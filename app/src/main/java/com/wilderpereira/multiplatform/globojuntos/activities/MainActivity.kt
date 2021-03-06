package com.wilderpereira.multiplatform.globojuntos.activities

import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import com.wilderpereira.multiplatform.globojuntos.models.Challenge
import com.wilderpereira.multiplatform.globojuntos.R
import com.wilderpereira.multiplatform.globojuntos.adapters.ChallengesAdapter
import com.wilderpereira.multiplatform.globojuntos.models.ShareInfo
import com.wilderpereira.multiplatform.globojuntos.repository.hasAlreadyVoted
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        questionsRv.apply {
            layoutManager = LinearLayoutManager(this@MainActivity) as RecyclerView.LayoutManager?
            adapter = ChallengesAdapter(loadChallenges()) { challenge -> goToChallengeInfo(challenge)}
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

    }

    private fun loadChallenges(): List<Challenge> {
        return listOf(

                Challenge(
                        "1000",
                        "Pitch Hackathon Globo Juntos!",
                        "De que forma você quer que terminemos o pitch?",
                        "AO VIVO",
                        "Na piscina",
                        "No confessionário",
                        Challenge("", "Legal, o que achou da nossa solução?",
                                "Conte para gente!?",
                                "AO VIVO",
                                "Muito boa!",
                                "Ótima!"),
                        365,
                        shareInfo = ShareInfo("Muito obrigado!",
                                "Fiquem atentos, no final do dia conheceremos os vencedores!",
                                "Aproveite e continue vendo soluções incríveis ;)"),
                        image = (getDrawable(R.drawable.grupo) as BitmapDrawable).bitmap,
                        color = R.color.green
                ),

                Challenge(
                        "1",
                        "Solta os Cachorros!",
                        "Até na páscoa a gente é MAIS VOCÊ! " +
                                "Qual receitinha você quer ver a Ana Maria Braga fazer para deixar essa data ainda mais deliciosa?",
                        "AO VIVO",
                        "Ovo de chocolate trufado",
                        "Ovo de chocolate com recheio de limão",
                        Challenge("", "Hm, até eu quero!",
                                "Falando em páscoa, conta pra gente: que tipo de pessoa você é?",
                                "AO VIVO",
                                "Que gosta de tudo #Formiga",
                                "Prefere chocolate branco ou ao leite",
                                option3 = "Meio amargo ou zero açúcar #TeamFitness"),
                        4,
                        shareInfo = ShareInfo("Mandou bem!",
                                "Fique ligado, logo depois do intervalo a receita será revelada!",
                                "Aproveita e compartilha com seus amigos. Quem sabe eles não fazem a receita pra você ;)"),
                        image = (getDrawable(R.drawable.anamaria) as BitmapDrawable).bitmap,
                        color = R.color.pink
                ),

                Challenge(
                        "2",
                        "Aniversário do Bonner",
                        "O JN está em festa! Hoje é aniversário do Bonner e ele está sem tempo de escolher qual terno usar. Ajude a decidir!",
                        "PRÓXIMO",
                        "Terno azul marinho",
                        "Terno pretinho básico",
                        Challenge("", "Boa, agora diz ai",
                                "Qual dia você faz aniversário? Assim a gente pode te ajudar a escolher o que assistir nessa data especial ;)",
                                "PRÓXIMO",
                                "",
                                "",
                                option3 = ""),
                        -1,
                        shareInfo = ShareInfo("Mandou bem!",
                                "Fique ligado, logo depois do intervalo a roupa escolhida será revelada!",
                                "Aproveita e compartilha com seus amigos. ;)"),
                        image = (getDrawable(R.drawable.bonner) as BitmapDrawable).bitmap,
                        color = R.color.blue1
                ),

                Challenge(
                        "",
                        "Votação encerrada",
                        "",
                        "Encerrados",
                        "Ovo de chocolate trufado",
                        "",
                        Challenge("", "",
                                "",
                                "",
                                "",
                                "",
                                option3 = ""),
                        -1,
                        shareInfo = ShareInfo("",
                                "",
                                ""),
                        image = (getDrawable(R.drawable.anamaria) as BitmapDrawable).bitmap
                ),

                Challenge(
                        "",
                        "Parabéns \nVocê votou e ganhou",
                        "",
                        "Encerrados",
                        "Ovo de chocolate trufado",
                        "",
                        Challenge("", "",
                                "",
                                "",
                                "",
                                "",
                                option3 = ""),
                        -1,
                        shareInfo = ShareInfo("",
                                "",
                                ""),
                        image = (getDrawable(R.drawable.thiago) as BitmapDrawable).bitmap
                )

        )
    }

    private fun goToChallengeInfo(challenge: Challenge) {

        if (challenge.id == "1000" && hasAlreadyVoted(this)) {
            Snackbar.make(findViewById(android.R.id.content), "Você já registrou seu voto!", Snackbar.LENGTH_SHORT)
                    .show()
        } else {
            val intent = Intent(this@MainActivity, ChallengeInfoActivity::class.java)
            intent.putExtra("question", challenge)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
