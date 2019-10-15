package com.serifyasar.kuran_ikelimeler.Activity


import android.media.AudioAttributes
import android.media.AudioManager
import android.media.SoundPool
import android.os.AsyncTask
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import com.google.android.material.chip.Chip
import com.serifyasar.kuran_ikelimeler.Db.AyetRepository
import com.serifyasar.kuran_ikelimeler.Db.Kelimeler
import com.serifyasar.kuran_ikelimeler.Fragment.SuccessFragment
import com.serifyasar.kuran_ikelimeler.R
import kotlinx.android.synthetic.main.activity_quiz.*
import java.util.*

class QuizActivity : AppCompatActivity() {
     private var i=0
     private var cntrl=0
    private lateinit var word: Kelimeler
    private var total=0
    private var id: Double=0.0
    private var wordList: ArrayList<Kelimeler> = arrayListOf()
    private var answerList: ArrayList<Kelimeler> = arrayListOf()
    private var optionList = Array(4){""}
    private var soundPool: SoundPool? =null
    private var soundId:Int=0
    val repository by lazy { AyetRepository(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        val p: Int= intent.getIntExtra("id",0)
        id= p.toDouble()
      //  val name: String= intent.getStringExtra("name")

        wordList= listeAsenkronTask(repository).execute(p).get() as ArrayList<Kelimeler>
        answerList= wordList.clone() as ArrayList<Kelimeler>

        total=wordList.size

        i=100/total


        sesAyar()

        Handler().postDelayed({
            progLoad.visibility=View.GONE
            btnAnswer.visibility=View.VISIBLE
            btnAnswer.text="Devam"
            if(total>2) {

                chip_group.visibility=View.VISIBLE
                question_tv.visibility=View.VISIBLE
                btnAnswer.visibility=View.VISIBLE
                newQuestion()
            }

        }, 800)




        btnAnswer.setOnClickListener {



            if(wordList.size==0)
                progStatus.progress=100

            if (chip_group.visibility==View.GONE){
                chip_group.visibility=View.VISIBLE
                question_tv.visibility=View.VISIBLE
                btnAnswer.visibility=View.VISIBLE
            }
            if(wordList.size>=0 && answerList.size>3) {

                if(btnAnswer.text=="Kontrol Et") {
                    kontrol()


                    lineControl.visibility=View.VISIBLE
                    cntrl=1
                }else{

                    if(wordList.size==0) {

                        val bundle=Bundle()
                        bundle.putInt("lessonIndex",id.toInt())
                        val fragment = SuccessFragment()
                        fragment.arguments=bundle
                        val transaction = supportFragmentManager.beginTransaction()
                        transaction.replace(R.id.mainContainer,fragment)
                        transaction.addToBackStack(null)
                        transaction.commit()
                        chip_group.visibility=View.GONE
                        question_tv.visibility=View.GONE
                        btnAnswer.visibility=View.GONE



                      }else{
                        newQuestion()
                        progStatus.progress+=i
                    }
                    btnAnswer.isEnabled=false
                    cntrl=0
                    lineControl.visibility=View.GONE
                }
            }
                else{
                Toast.makeText(this,"Bitti",Toast.LENGTH_LONG).show()
            }



        }


        option_a_chip.setOnClickListener { if (option_a_chip.isChecked) btnStil1() else btnStil2() }
        option_b_chip.setOnClickListener { if (option_b_chip.isChecked) btnStil1() else btnStil2() }
        option_c_chip.setOnClickListener { if (option_c_chip.isChecked) btnStil1() else btnStil2() }
        option_d_chip.setOnClickListener { if (option_d_chip.isChecked) btnStil1() else btnStil2() }



    }


    private fun sesAyar() {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            val audioAttributes=AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_GAME)
                .build()


            soundPool=SoundPool.Builder().setMaxStreams(2).setAudioAttributes(audioAttributes)
                .build()
        }else{
            soundPool=SoundPool(2,AudioManager.STREAM_MUSIC,0)
        }

        soundId= soundPool!!.load(this,R.raw.tada,1)
    }

    private fun kontrol() {
        val answer = findViewById<Chip>(chip_group.checkedChipId)
        if (word.turkish==answer.text)
        {  txtDurum.text="Doğru"
            wordList.removeAt(0)

        }
        else
            txtDurum.text="Yanlış \nDoğru Cevap : " + word.turkish

        btnAnswer.text="Devam"
        chip_group.clearCheck()


        soundPool!!.play(soundId, 1F, 1F,1,0, 1F)




    }


    private fun btnStil1(){

        btnAnswer.isEnabled=true
        btnAnswer.text="Kontrol Et"
       if(cntrl==1) {
           chip_group.clearCheck()
           btnAnswer.text="Devam"
           Toast.makeText(this,"Sonraki soruya geçiniz",Toast.LENGTH_SHORT).show()
       }

    }
    private fun btnStil2(){
        btnAnswer.isEnabled=false
        btnAnswer.text="Devam"

    }
    private fun diziShufle(){
        optionList.forEach {
            val n:Int=Random().nextInt(4)
           val temp=optionList[0]
            optionList[0]=optionList[n]
            optionList[n]=temp


        }


    }
    private fun newQuestion() {

        Collections.shuffle(wordList)
        Collections.shuffle(answerList)
        word=wordList[0]

        val x=answerList.indexOf(word)

        if(x<3){
            answerList[x]=answerList.last()
            answerList[total-1]=word
        }

        optionList[0]=word.turkish
        optionList[1]=answerList[0].turkish
        optionList[2]=answerList[1].turkish
        optionList[3]=answerList[2].turkish

        diziShufle()
        question_tv.text=word.arabic.toString()

        option_a_chip.text=optionList[0]
        option_b_chip.text=optionList[1]
        option_c_chip.text=optionList[2]
        option_d_chip.text=optionList[3]





    }

    private class listeAsenkronTask(val repository: AyetRepository) : AsyncTask<Int, Void, List<Kelimeler>>() {
        override fun doInBackground(vararg params: Int?): List<Kelimeler>? {
            val list = params[0]?.let { repository.kelimeListe(it) }

            return list
        }

    }


}
