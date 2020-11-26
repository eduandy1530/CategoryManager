package com.eica.categorymanager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eica.categorymanager.model.Category
import kotlinx.android.synthetic.main.fragment_add_category.*
import org.jetbrains.anko.okButton
import org.jetbrains.anko.support.v4.alert

class AddCategoryFragment : Fragment() {

    private lateinit var database:DataManagerControl
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        database = DataManagerControl(requireContext())
        return inflater.inflate(R.layout.fragment_add_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_Categories.adapter = CategoryAdapter(this.requireActivity(),requireContext(),database.getAllCategory())
        rv_Categories.layoutManager = LinearLayoutManager(requireContext())

    }
    override fun onStart() {
        super.onStart()
        btnGuardar.setOnClickListener {
           if(editTextCategoryName.text.isNotEmpty()){
               database.addCategory(Category(
                   name = editTextCategoryName.text.toString()
               ))
               alert("Se agrego su categoria"){ okButton {
                activity?.onBackPressed()
               } }.show()
           }
        }
    }

    companion object {
        fun newInstance() =
            AddCategoryFragment()
    }
}