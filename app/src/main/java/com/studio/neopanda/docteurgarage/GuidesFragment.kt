package com.studio.neopanda.docteurgarage

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_guides.*

class GuidesFragment : Fragment() {

    val guides: ArrayList<Guide> = ArrayList()

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
        guides_rv.adapter = GuidesAdapter(guides, this)
    }

    private fun addGuides() {
        guides.add(Guide(
                "Faire le niveau d'huile",
                "25-02-2021",
                "Préparation : \n" +
                        "- Un chiffon\n" +
                        "- De l'huile moteur adaptée à votre véhicule\n\n" +
                        "Vérification : \n" +
                        "1) Localiser l'indicateur de la jauge de niveau d'huile (le plus souvent, " +
                        "il s'agit d'une longue + languette avec un embout qui se dévisse au bout.)\n" +
                        "2) Tirer l'indicateur et essuyer l'extrémité avec le chiffon.\n" +
                        "3) Remettre l'indicateur au fond puis le retirer, puis vérifier que l'huile" +
                        " se situe bien entre les indicateurs MIN et MAX du bas de la languette.\n" +
                        "4) Si le niveau est inférieur à MIN, rajouter de l'huile moteur doucement" +
                        "et réitérer la vérification afin de ne pas dépasser le niveau MAX."
            )
        )
        guides.add(Guide("Changer une roue", "20-02-2021", "Change la roue"))
    }
}