package com.example.textmyprofessorstudent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.textmyprofessorstudent.databinding.FragmentChatRoomBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import android.os.Handler
import android.widget.Button
import android.widget.ImageButton
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.delay
import java.util.concurrent.TimeUnit

class ChatRoomFragment : Fragment() {

    private lateinit var database: DatabaseReference
    val handler = Handler()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val binding = DataBindingUtil.inflate<FragmentChatRoomBinding>(inflater,
            R.layout.fragment_chat_room,container,false)

        database = FirebaseDatabase.getInstance().reference

        val args = ChatRoomFragmentArgs.fromBundle(arguments!!)
        val room_id = args.roomid

        binding.chatBox.layoutManager = LinearLayoutManager(this.context)
        binding.chatBox.adapter = MessageAdapter(database.child("chat-rooms").child(room_id).child("messages"), binding.chatBox)

        // Send Button Listener
        binding.sendBtn.setOnClickListener{
            //The editText that the student will use as input
            val text = binding.inputMsgText.text.toString()
            //Creates a new entry in the database in "chat-rooms" with name "Professor at *DATE*" and sets the value to the input

            // Do nothing if the the field is blank
            if(text.isEmpty()) {
                Toast.makeText(this.context,"Enter a message", Toast. LENGTH_SHORT).show()
            }
            else {

                val date = Date()
                val msg = Message(time = date.toString(), user = "Student", text = text)
                val autoGenKey = database.child("chat-rooms").child(room_id).child("messages").push()
                val key: String = autoGenKey.key.toString()
                database.child("chat-rooms").child(room_id).child("messages").child(key).setValue(msg)
//            Log.d(TAG, "onChildAdded:" + DataSnapshot.getValue(Message::class.javaObjectType)!!)

                //Clear the text after submitting
                binding.inputMsgText.setText("")

                binding.sendBtn.setEnabled(false)
                Handler().postDelayed({
                    binding.sendBtn.setEnabled(true)
                }, 2000)
            }
        }

        // Text Editor IME Action Listener
        binding.inputMsgText.setOnEditorActionListener { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_SEND ){
                val text = binding.inputMsgText.text.toString()
                //Creates a new entry in the database in "chat-rooms" with name "Professor at *DATE*" and sets the value to the input

                // Do nothing if the the field is blank
                if(text.isEmpty()) {
                    Toast.makeText(this.context,"Enter a message",Toast. LENGTH_SHORT).show()
                }
                else {

                    val date = Date()
                    val msg = Message(time = date.toString(), user = "Student", text = text)
                    val autoGenKey = database.child("chat-rooms").child(room_id).child("messages").push()
                    val key: String = autoGenKey.key.toString()
                    database.child("chat-rooms").child(room_id).child("messages").child(key).setValue(msg)
                    //Clear the text after submitting
                    binding.inputMsgText.setText("")
                }
                true
            }
            else{
                false
            }
        }

        binding.repeatThat.setOnClickListener{
            val text = binding.repeatThat.text.toString()
            val date = Date()
            val msg = Message(time = date.toString(), user = "Student", text = text)
            val autoGenKey = database.child("chat-rooms").child(room_id).child("messages").push()
            val key: String = autoGenKey.key.toString()
            database.child("chat-rooms").child(room_id).child("messages").child(key).setValue(msg)

            binding.repeatThat.setEnabled(false)
            Handler().postDelayed({
                binding.repeatThat.setEnabled(true)
            }, 5000)
        }

        binding.moreDetails.setOnClickListener{
            val text = binding.moreDetails.text.toString()

            val date = Date()
            val msg = Message(time = date.toString(), user = "Student", text = text)
            val autoGenKey = database.child("chat-rooms").child(room_id).child("messages").push()
            val key: String = autoGenKey.key.toString()
            database.child("chat-rooms").child(room_id).child("messages").child(key).setValue(msg)

            binding.moreDetails.setEnabled(false)
            Handler().postDelayed({
                binding.moreDetails.setEnabled(true)
            }, 5000)
        }

        binding.previousSlide.setOnClickListener{
            val text = binding.previousSlide.text.toString()

            val date = Date()
            val msg = Message(time = date.toString(), user = "Student", text = text)
            val autoGenKey = database.child("chat-rooms").child(room_id).child("messages").push()
            val key: String = autoGenKey.key.toString()
            database.child("chat-rooms").child(room_id).child("messages").child(key).setValue(msg)

            binding.previousSlide.setEnabled(false)
            Handler().postDelayed({
                binding.previousSlide.setEnabled(true)
            }, 5000)
        }

        binding.cantReadTooSmall.setOnClickListener{
            val text = binding.cantReadTooSmall.text.toString()

            val date = Date()
            val msg = Message(time = date.toString(), user = "Student", text = text)
            val autoGenKey = database.child("chat-rooms").child(room_id).child("messages").push()
            val key: String = autoGenKey.key.toString()
            database.child("chat-rooms").child(room_id).child("messages").child(key).setValue(msg)

            binding.cantReadTooSmall.setEnabled(false)
            Handler().postDelayed({
                binding.cantReadTooSmall.setEnabled(true)
            }, 5000)
        }

        binding.isThisOnTestExam.setOnClickListener{
            val text = binding.isThisOnTestExam.text.toString()

            val date = Date()
            val msg = Message(time = date.toString(), user = "Student", text = text)
            val autoGenKey = database.child("chat-rooms").child(room_id).child("messages").push()
            val key: String = autoGenKey.key.toString()
            database.child("chat-rooms").child(room_id).child("messages").child(key).setValue(msg)

            binding.isThisOnTestExam.setEnabled(false)
            Handler().postDelayed({
                binding.isThisOnTestExam.setEnabled(true)
            }, 5000)
        }

        return binding.root
    }

}
