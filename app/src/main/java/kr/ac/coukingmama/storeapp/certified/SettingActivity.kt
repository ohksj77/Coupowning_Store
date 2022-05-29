package kr.ac.coukingmama.storeapp.certified

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.kakao.sdk.user.UserApiClient
import kr.ac.coukingmama.storeapp.before.LoginActivity
import kr.ac.coukingmama.storeapp.databinding.ActivitySettingBinding


class SettingActivity : AppCompatActivity() { // 설정 페이지

    lateinit var binding : ActivitySettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.back.setOnClickListener{
            finish()
        }

        binding.logout.setOnClickListener{
            val alert : android.app.AlertDialog = android.app.AlertDialog.Builder(this)
                .setTitle("로그아웃하시겠습니까?")
                .setNegativeButton("취소", null)
                .setPositiveButton("로그아웃") { dialog, _ -> dialog.dismiss()
                    UserApiClient.instance.logout { error ->
                        if(error != null){
                            Log.e("error", "로그아웃 실패. SDK에서 토큰 삭제됨", error)
                        }
                        else{
                            Log.i("success", "로그아웃 성공. SDK에서 토큰 삭제됨")
                            val intent = Intent(applicationContext, LoginActivity::class.java)
                            startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                            finish()
                        }
                    }
                }.create()
            alert.setOnShowListener {
                alert.getButton(android.app.AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK)
                alert.getButton(android.app.AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK)
            }
            alert.show()
        }
    }
}