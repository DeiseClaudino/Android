package br.com.alura.ageda.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.widget.Toast;

import br.com.alura.ageda.dao.AlunoDao;

public class SMSReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Object[] pdus = (Object[]) intent.getSerializableExtra("pdus");
        byte[] pdu = (byte[]) pdus[0];
        String[] formato = (String[]) intent.getSerializableExtra("format");

        SmsMessage sms = SmsMessage.createFromPdu(pdu, formato);

        String telefone = sms.getDisplayOriginatingAddress();

        AlunoDao dao = new AlunoDao(context);

        if (dao.ehAluno(telefone)) {
            Toast.makeText(context, "Chegou um SMS!", Toast.LENGTH_SHORT).show();
        }
        dao.close();
    }
}
