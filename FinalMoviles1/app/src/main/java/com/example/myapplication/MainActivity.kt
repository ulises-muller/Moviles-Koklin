package com.example.myapplication

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.MyApplicationTheme
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()
                NavHost(navController, startDestination = "Sistema") {
                    composable("Sistema") { Sistema(navController) }
                }
            }
        }
    }
}


val client = OkHttpClient()
data class Task (
    val id: String,
    val task: String,
    val dni: String,
    val nroMembresia: String
)
fun parseResponse(responseData: String): List<Task> {
    val jsonArray = JSONArray(responseData)
    val tasks = mutableListOf<Task>()
    for (i in 0 until jsonArray.length()) {
        val jsonObject = jsonArray.getJSONObject(i)
        val task = jsonObject.getString("task")
        val id = jsonObject.getString("id")
        val dni = jsonObject.getString("dni")
        val nroMembresia = jsonObject.getString("nroMembresia")
        tasks.add(Task(id, task, dni, nroMembresia))
    }
    return tasks
}
@OptIn(DelicateCoroutinesApi::class)
fun verDatos(onTasksFetched: (List<Task>) -> Boolean) {
    GlobalScope.launch(Dispatchers.IO) {
        val request = Request.Builder()
            .url("https://665e417b1e9017dc16ef7651.mockapi.io/lista")
            .build()
        val response = client.newCall(request).execute()
        val responseData = response.body?.string()
        launch(Dispatchers.Main) {
            if (responseData != null) {
                val tasks = parseResponse(responseData)
                onTasksFetched(tasks)
            } else {
                showError("No se pudo obtener la respuesta")
            }
        }
    }
}
private fun showError(message: String) {
    Log.d("Error conexion", message)
}

@OptIn(DelicateCoroutinesApi::class)
fun agregarApi(task: String, id: String, dni: String, nroMembresia: String) {
    GlobalScope.launch(Dispatchers.IO) {
        val json = """{
                        "task": "$task",
                        "id": "$id" ,
                        "dni": "$dni" ,
                        "nroMembresia": "$nroMembresia"
                        }
                        """.trimIndent()
        val requestBody = json.toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
        val request = Request.Builder()
            .url("https://665e417b1e9017dc16ef7651.mockapi.io/lista")
            .post(requestBody)
            .build()
        val response = client.newCall(request).execute()
        val responseData = response.body?.string()
        launch(Dispatchers.Main) {
            if (responseData != null) {
                Log.d("Agregar Tarea", "Respuesta: $responseData")
            } else {
                showError("No se pudo agregar la tarea")
            }
        }
    }
}
@OptIn(DelicateCoroutinesApi::class)
fun borrarUsuario(taskId: String, onResult: (Boolean) -> Unit) {
    GlobalScope.launch(Dispatchers.IO) {
        val request = Request.Builder()
            .url("https://665e417b1e9017dc16ef7651.mockapi.io/lista/$taskId")
            .delete()
            .build()
        val response = client.newCall(request).execute()
        launch(Dispatchers.Main) {
            if (response.isSuccessful) {
                onResult(true)
            } else {
                onResult(false)
            }
        }
    }
}
fun modificarUsuario(task: String, id: String, dni: String, nroMembresia: String) {
    GlobalScope.launch(Dispatchers.IO) {
        val json = """{
                        "task": "$task",
                        "id": "$id" ,
                        "dni": "$dni" ,
                        "nroMembresia": "$nroMembresia"
                        }
                        """.trimIndent()
        val requestBody = json.toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
        val request = Request.Builder()
            .url("https://665e417b1e9017dc16ef7651.mockapi.io/lista/$id")
            .put(requestBody)
            .build()
        val response = client.newCall(request).execute()
        val responseData = response.body?.string()
        launch(Dispatchers.Main) {
            if (responseData != null) {
                Log.d("Agregar Tarea", "Respuesta: $responseData")
            } else {
                showError("No se pudo agregar la tarea")
            }
        }
    }
}
fun generateRandomNumber(min: Int, max: Int): Int {
    return (min..max).random()
}

@Composable
private fun Sistema(navController: NavHostController) {

    val texto by remember { mutableStateOf("Miembros") }
    var textoInput by remember { mutableStateOf("") }
    val tasks = remember { mutableStateListOf<Task>() }
    var dniInput by remember { mutableStateOf("") }
    fun reload(){
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            verDatos{ newTasks -> tasks.addAll(newTasks) }
            tasks.clear()
        }, 500)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box( modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center ) {
            Text(
                text = texto,
                fontSize = 25.sp,
                lineHeight = 116.sp,
                color = Color.Blue
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Conexion backend")
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                TextField(
                    value = textoInput,
                    onValueChange = { textoInput = it },
                    label = { Text("Nombre del miembro") }
                )
            }
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                TextField(
                    value = dniInput,
                    onValueChange = { dniInput = it },
                    label = { Text("Dni") }
                )
            }

            Button(onClick = {
                agregarApi(textoInput, "", dniInput, generateRandomNumber(10000000, 99999999).toString())
                reload()

            }) {
                Text("Agregar Miembro")
            }
            Button(onClick = {
                verDatos{ newTasks -> tasks.addAll(newTasks) }
                tasks.clear()
            }) {
                Text("resfrescar")
            }

            LazyColumn(
                modifier = Modifier.padding(16.dp)
            ) {
                itemsIndexed(tasks) { index, task ->
                    Text("Nombre:" +task.task)
                    Text("Dni:"+task.dni)
                    Text("NroMembresia:" +task.nroMembresia)
                    Row{
                        IconButton(onClick = {
                            borrarUsuario(task.id) { success ->
                                if (success) {
                                    tasks.remove(task)
                                } else {
                                    showError("No se pudo eliminar la tarea")
                                }
                            }
                        })
                        {
                            Icon(Icons.Default.Delete, contentDescription = "Borrar tarea")
                        }
                        IconButton(onClick = {
                            if (task.dni == dniInput){
                            modificarUsuario(textoInput, task.id, dniInput, task.nroMembresia)
                                //!!!!!
                                ///!!!!
                                //!!!!
                            reload()} else { showError("No se pudo modificar la tarea") }
                            //EN EL ELSE DE AHI ARRIBA TENES QUE PONER LA ALERTAAAAA
                                //!!!!
                                //!!!!
                            //!!!!!
                        }) {
                            Icon(Icons.Default.Edit, contentDescription = "editar")
                        }
                    }
                }
            }
        }
    }

}