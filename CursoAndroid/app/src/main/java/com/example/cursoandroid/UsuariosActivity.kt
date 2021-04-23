package com.example.cursoandroid

import android.content.ContentValues
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.annotation.RequiresApi

class UsuariosActivity : AppCompatActivity() {

    val USUARIO_NO_EXISTE = "El usuario no existe"

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_usuarios)

        val btnAgregar = findViewById<Button>(R.id.botonAgregar)
        val btnEliminar = findViewById<Button>(R.id.botonEliminar)
        val btnBuscar = findViewById<Button>(R.id.botonBuscar)
        val btnModificar = findViewById<Button>(R.id.botonModificar)

        btnAgregar.setOnClickListener {
            agregarUsuario()
        }

        btnEliminar.setOnClickListener {
            eliminarUsuario()
        }

        btnBuscar.setOnClickListener {
            buscarUsuario()
        }

        btnModificar.setOnClickListener {
            modificarUsuario()
        }

        listarUsuarios()
    }

    @RequiresApi(Build.VERSION_CODES.P)
    fun listarUsuarios() {

        val listaUsuarios = arrayListOf<String>()

        // referencias componentes view
        val vLista = findViewById<ListView>(R.id.lvUsuarios)

        // base de datos
        val usuarios = BaseDeDatos(this)
        val db = usuarios.writableDatabase
        val filas = db.rawQuery("select * from usuarios", null)

        // recorremos los registros
        if (filas.moveToFirst()) {
            // por cada registro, agregamos el id
            do {
                Log.d("FILA_NOMBRE", filas.getString(1))

                listaUsuarios.add(
                                filas.getString(0) +
                        " - " + filas.getString(1) +
                        " - " + filas.getString(2)
                )
            }
            while (filas.moveToNext())
        }
        db.close()

        // adaptadores
        val adaptador = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaUsuarios)
        vLista.adapter = adaptador

    }

    @RequiresApi(Build.VERSION_CODES.P)
    fun agregarUsuario() {

        // referencias componentes view
        val vNombre = findViewById<EditText>(R.id.nombre)
        val vEmail = findViewById<EditText>(R.id.email)

        // base de datos
        val usuarios = BaseDeDatos(this)
        val db = usuarios.writableDatabase

        // contenedor de valores para cada registro
        val nuevoUsuario = ContentValues()
        nuevoUsuario.put("nombre", vNombre.text.toString())
        nuevoUsuario.put("email", vEmail.text.toString())

        // insertamos los valores en la base de datos
        db.insert("usuarios", null, nuevoUsuario)
        db.close()

        // limpiar campos
        vNombre.setText("")
        vEmail.setText("")

        // hacer foco al componente view nombre
        vNombre.requestFocus()

        // mostramos una notificacion al usuario en caso de exito
        Utilidades.notificacion(this, "Usuario agregado con exito!")

        // actualizar lista con el registro agregado
        listarUsuarios()

    }

    @RequiresApi(Build.VERSION_CODES.P)
    fun eliminarUsuario() {

        //referencias componentes view
        val vIdUsuario = findViewById<EditText>(R.id.eliminarId)

        // base de datos
        val usuarios = BaseDeDatos(this)
        val db = usuarios.writableDatabase

        // consulta para eliminar usuario
        val consultaParaEliminar = "id=${vIdUsuario.text.toString()}"
        val metodoParaEliminar = db.delete("usuarios", consultaParaEliminar, null)
        db.close()

        // encontro un registro y lo elimino
        if (metodoParaEliminar == 1) {
            Utilidades.notificacion(this, "Se elimino el usuario!")

            // blanquear o resetear EditTexts
            vIdUsuario.setText("")

            // actualizar lista con el registro agregado
            listarUsuarios()
        }
        else {
            Utilidades.notificacion(this, "El usuario no existe")
        }

    }

    // TODO: eliminar uso de RequiresApi
    @RequiresApi(Build.VERSION_CODES.P)
    fun buscarUsuario() {

        // referencias componentes view
        val vIdUsuario = findViewById<EditText>(R.id.eliminarId)
        val vNombre = findViewById<EditText>(R.id.nombre)
        val vEmail = findViewById<EditText>(R.id.email)

        if (vIdUsuario.text.isNullOrEmpty()) {
            Utilidades.notificacion(this, "Completar el campo ID")
        } else {

            // base de datos
            val usuarios = BaseDeDatos(this)
            val db = usuarios.writableDatabase

            // consulta para buscar usuario por id
            val consultaBuscarPorId = "SELECT * FROM usuarios WHERE id=${vIdUsuario.text.toString()}"
            val usuario = db.rawQuery(consultaBuscarPorId, null)

            if (usuario.moveToFirst()) {
                Utilidades.notificacion(this, "Usuario recuperado correctamente")

                // rellenamos los EditText del Alta de Usuario con los datos recuperados
                vNombre.setText(usuario.getString(1))
                vEmail.setText(usuario.getString(2))

            } else {
                Utilidades.notificacion(this, USUARIO_NO_EXISTE)
                vNombre.setText("")
                vEmail.setText("")
            }

            db.close()

        }

    }

    @RequiresApi(Build.VERSION_CODES.P)
    fun modificarUsuario() {

        // referencias componentes view
        val vIdUsuario = findViewById<EditText>(R.id.eliminarId)
        val vNombre = findViewById<EditText>(R.id.nombre)
        val vEmail = findViewById<EditText>(R.id.email)

        // comprobacion del campo id
        if (vIdUsuario.text.isNullOrEmpty()) {
            Utilidades.notificacion(this, "Completar el campo ID")
        } else {

            // base de datos
            val usuarios = BaseDeDatos(this)
            val db = usuarios.writableDatabase

            // contenedor de valores para cada registro
            val nuevosDatos = ContentValues()
            nuevosDatos.put("nombre", vNombre.text.toString())
            nuevosDatos.put("email", vEmail.text.toString())

            val consultaSQL = "id=${vIdUsuario.text.toString()}"
            val filaModificada = db.update("usuarios", nuevosDatos, consultaSQL, null)

            db.close()

            if (filaModificada == 1) {
                // mostramos una notificacion al usuario en caso de exito
                Utilidades.notificacion(this, "Usuario modificado con exito!")

                // actualizar lista con el registro agregado
                listarUsuarios()
            } else {
                // mostramos una notificacion al usuario en caso de fallo
                Utilidades.notificacion(this, "Error al modificar usuario")
            }

        }

    }
}