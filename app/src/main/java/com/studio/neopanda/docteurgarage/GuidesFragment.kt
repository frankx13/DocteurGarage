package com.studio.neopanda.docteurgarage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_guides.*

class GuidesFragment : Fragment() {

    private val guides: ArrayList<Guide> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_guides, container, false)

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        addGuides()
        guides_rv.layoutManager = LinearLayoutManager(activity)
        guides_rv.adapter = GuidesAdapter(guides, activity!!)
    }

    private fun addGuides() {
        guides.add(
            Guide(
                0,
                "Faire le niveau d'huile",
                "25-02-2021",
                "Préparation : \n" +
                        "- Un chiffon\n" +
                        "- De l'huile moteur adaptée à votre véhicule\n\n" +
                        "Action : \n" +
                        "1) Localiser l'indicateur de la jauge de niveau d'huile (le plus souvent, " +
                        "il s'agit d'une longue + languette avec un embout qui se dévisse au bout.)\n" +
                        "2) Tirer l'indicateur et essuyer l'extrémité avec le chiffon.\n" +
                        "3) Remettre l'indicateur au fond puis le retirer, puis vérifier que l'huile" +
                        " se situe bien entre les indicateurs MIN et MAX du bas de la languette.\n" +
                        "4) Si le niveau est inférieur à MIN, rajouter de l'huile moteur doucement" +
                        "et réitérer la vérification afin de ne pas dépasser le niveau MAX."
            )
        )
        guides.add(
            Guide(
                1,
                "Changer une roue", "24-02-2021", "Préparation : \n" +
                        "- Une roue de secours \n" +
                        "- Un crick ou tout moyen permettant de surélever sans danger le véhicule \n" +
                        "- Une clef en croix (= démonte-roue)\n\n" +
                        "Action :\n" +
                        "1) Placer le véhicule sur une surface plate et éteignez le contact.\n" +
                        "2) Repérez l'emplacement prévu pour surélever la voiture près de la roue à" +
                        " remplacer (on le reconnait car c'est l'endroit qui semble le plus résistant" +
                        " et est de forme arrondi comme le haut du krick). \n" +
                        "3) Positionnez le krick sous l'emplacement prévu. \n" +
                        "4) Faites monter le krick jusqu'à ce que l'entièreté de la roue soit soulevé.\n" +
                        "5) Enlevez les écrous avec la clef en croix (on tourne vers la gauche pour dévisser). \n" +
                        "6) Enlevez la roue en la tirant vers vous. Remplacez par la roue de secours. \n" +
                        "7) Remettez les écrous en position, puis faites redescendre le krick doucement.\n\n" +
                        " Le tour est joué."
            )
        )
        guides.add(
            Guide(
                2,
                "Surchauffe moteur",
                "26-02-2021",
                "Comment réagir en cas de surchauffe moteur ?"
            )
        )
        guides.add(
            Guide(
                3,
                "Amplifier le signal radio",
                "22-02-2021",
                "Voici une petite astuce pour augmenter la portée de réception de votre " +
                        "autoradio, vous aurez juste besoin d'une fourchette."
            )
        )
        guides.add(
            Guide(
                4,
                "Informations utiles",
                "20-02-2021",
                "Voici une collection d'informations universelles sur votre voiture qui " +
                        "vous serviront au moins une fois à coup sur."
            )
        )
        guides.add(
            Guide(
                5,
                "Vitre cassée", "16-02-2021", "Change la roue"
            )
        )
    }
}