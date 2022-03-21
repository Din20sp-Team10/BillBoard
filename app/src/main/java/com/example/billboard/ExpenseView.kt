package com.example.billboard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
<<<<<<< HEAD
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
=======
>>>>>>> 94210a1a64dca181564c42fd804cf79676c897cf
import androidx.navigation.NavController
import com.google.firebase.firestore.DocumentSnapshot

@Composable
<<<<<<< HEAD
fun ExpenseView(expense : DocumentSnapshot, expenseNavControl: NavController) {

    val expenseName = expense.get("name").toString()
    val expenseAmount = expense.get("amount").toString()
    val expensePayer = expense.get("payer").toString()
=======
fun ExpenseView( expense: DocumentSnapshot, expenseNavControl: NavController) {
//    //TODO fetch expense info in database
//
    val expenseName = expense.get("name")
    val expenseAmount = expense.get("amount")
    val expensePayer = expense.get("payer")
>>>>>>> 94210a1a64dca181564c42fd804cf79676c897cf
    val expenseRest = expense.get("rest") as List<String>

    Column(){
        Text(text = "Expense details : $expenseName")
        //TODO need to discuss about default currency, can the user choose one or the group
        Text(text = "$expenseAmount €")
        Text(text = "Payer member : $expensePayer")
        Text(text = "Members who have to pay :")
        expenseRest.forEach { member ->
            Row(){
                Text(text = member)
                Button(onClick = { /*TODO if the user is an admin I can erase the member debt*/ }) {Text(text = "Erase debt")}
            }
        }
        Row(
            horizontalArrangement = Arrangement.Start
        ){
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "back icon",
                modifier = Modifier.clickable {  expenseNavControl.navigate("group")  })
            OutlinedButton(onClick = { /*TODO Delete function if USER is an admin*/ }) {
                Text("Delete this expense")
            }
            OutlinedButton(onClick = {
                expenseNavControl.navigate("editExpense/${expense.id}/${expenseName}/${expenseAmount}/${expensePayer}/${expenseRest.joinToString(",")}") }) {
                Text("Edit this expense")
            }
        }

    }
}

<<<<<<< HEAD
fun getExpenseLine(expenseName : MutableState<String>,
                   expenseAmount : MutableState<String>,
                   expensePayer : MutableState<String>,
                   expenseRest : MutableState<List<String>>,
                   id : String){

    Firebase.firestore.collection("expenses")
        .document(id)
        .get()
        .addOnSuccessListener {

            var eName = it.get("name").toString()
            var eAmount =  it.get("amount").toString()
            var ePayer = it.get("payer").toString()
            var eRest = mutableListOf<String>()
            val list = it.get("rest") as? List<String>
            list!!.forEach { element ->
                eRest.add(element)
            }

            expenseName.value = eName
            expenseAmount.value = eAmount
            expensePayer.value = ePayer
            expenseRest.value = eRest

        }
}
=======
//fun getExpenseLine(expenseName : MutableState<String>,
//                   expenseAmount : MutableState<String>,
//                   expensePayer : MutableState<String>,
//                   expenseRest : MutableState<List<String>>,
//                   id : String){
//
//    Firebase.firestore.collection("expenses")
//        .document(id)
//        .get()
//        .addOnSuccessListener {
//
//            var eName = it.get("name").toString()
//            var eAmount =  it.get("amount").toString()
//            var ePayer = it.get("payer").toString()
//            var eRest = mutableListOf<String>()
//            val list = it.get("rest") as? List<String>
//            list!!.forEach { element ->
//                eRest.add(element)
//            }
//
//            expenseName.value = eName
//            expenseAmount.value = eAmount
//            expensePayer.value = ePayer
//            expenseRest.value = eRest
//
//        }
//}

>>>>>>> 94210a1a64dca181564c42fd804cf79676c897cf
