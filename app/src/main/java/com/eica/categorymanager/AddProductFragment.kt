package com.eica.categorymanager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import com.eica.categorymanager.model.Category
import com.eica.categorymanager.model.Product
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_add_category.*
import kotlinx.android.synthetic.main.fragment_add_product.*
import org.jetbrains.anko.noButton
import org.jetbrains.anko.okButton
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.yesButton

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "producto"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddProductFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddProductFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var database:DataManagerControl
    private var isEdit = false
    private var productId:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        database = DataManagerControl(requireContext())

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addProductButton.setOnClickListener {
            addProduct()
        }
        addCategoryButton.setOnClickListener {
            findNavController().navigate(R.id.action_addProductFragment_to_addCategoryFragment)
        }
        val categories = database.getAllCategory()
        catgorySpinner.adapter = ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item,categories )
        if(!param1.isNullOrEmpty()){
            isEdit = true

           // btnGuardar.setText("Update")
            val product = Gson().fromJson<Product>(param1, Product::class.java)
            productId = product.id!!
            editTextProductName.setText(product.name)
            editTextProductPrice.setText(product.price.toString())
            deleteProductButton.visibility = View.VISIBLE
            deleteProductButton.setOnClickListener {
                alert("Estas seguro que quieres eliminar este producto"){
                    yesButton {
                        database.deleteProduct(product.id!!)
                        findNavController().navigate(R.id.action_addProductFragment_to_productListFragment)
                    }
                    noButton {

                    }
                }
            }
        }

    }


    private fun addProduct(){
     if(editTextProductName.text.isNotEmpty() && editTextProductPrice.text.isNotEmpty()){
         if(!isEdit){
             database.addProduct(Product(
                 name = editTextProductName.text.toString(),
                 price = editTextProductPrice.text.toString().toInt(),
                 categoryId = (catgorySpinner.selectedItem as Category).id!!
             ))
             alert("Se agrego el producto") {
                 okButton {
                     activity?.onBackPressed()
                 }
             }.show()
         }else{
             database.editProduct(Product(
                 id = productId,
                 name = editTextProductName.text.toString(),
                 price = editTextProductPrice.text.toString().toInt(),
                 categoryId = (catgorySpinner.selectedItem as Category).id!!
             ))
             alert("Se actualizo el producto") {
                 okButton {
                     activity?.onBackPressed()
                 }
             }.show()
         }
     }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_product, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddProductFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddProductFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}