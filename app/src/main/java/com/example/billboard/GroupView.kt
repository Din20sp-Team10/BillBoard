package com.example.billboard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
<<<<<<< HEAD
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
=======
import androidx.compose.material.Scaffold
>>>>>>> 94210a1a64dca181564c42fd804cf79676c897cf
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.firestore.DocumentSnapshot

@Composable
fun GroupView( groupInfo: DocumentSnapshot, expenses: List<DocumentSnapshot>, expenseNavControl: NavController, navControl: NavController) {

    Scaffold(
        topBar = { TopBar(showMenu = true) },
        content = { GroupViewContent( groupInfo, expenses, expenseNavControl, navControl) }
    )

}

@Composable
fun GroupViewContent( groupInfo: DocumentSnapshot, expenses: List<DocumentSnapshot>, expenseNavControl: NavController, navControl: NavController){

    Column(){
        Column(){
            Text(text = "Name: ${groupInfo.get("name").toString()}", modifier = Modifier.clickable { navControl.navigate("MainScreen") })
            Text(text = "Admins: ${groupInfo.get("admins").toString()}", modifier = Modifier.clickable { navControl.navigate("MainScreen") })
            Text(text = "Members ${groupInfo.get("members").toString()}", modifier = Modifier.clickable { navControl.navigate("MainScreen") })
        }
        Column(){
            expenses.forEach{ expense ->
                Spacer(modifier = Modifier.height(5.dp))
                Card( modifier = Modifier
                    .width(240.dp)
                    .height(36.dp)
                    .clickable { expenseNavControl.navigate(expense.get("name").toString()) }
                ){
                    Text( text = expense.get("name").toString(), fontSize = 23.sp)
                }

            }
        }
        Row(){
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "back icon",
                modifier = Modifier.clickable {  navControl.navigate("MainScreen")  })
            FloatingActionButton(onClick = { expenseNavControl.navigate("addExpense") }) {
                Text(text = "+")
            }
        }
    }
}
