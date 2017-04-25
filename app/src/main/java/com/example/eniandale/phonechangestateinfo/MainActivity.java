package com.example.eniandale.phonechangestateinfo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.content.Context;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.TelephonyManager;
import android.widget.TextView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private TelephonyManager manager;
    private TextView text;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (TextView)findViewById(R.id.text);
        text.setText("Listener is stopped");

        manager = (TelephonyManager)getSystemService(
                Context.TELEPHONY_SERVICE);
    }




    private PhoneStateListener listener = new PhoneStateListener() {
        @Override
        public void onCallStateChanged(
                final int state, final String incomingNumber) {
            text.append("\nCall state:\t" +
                    convertCallStateToString(state) + "\nIncoming number:\t");
        }
        @Override
        public void onDataConnectionStateChanged(
                int state, int networkType) {
            text.append("\nNetwork Type:\t" +
                    convertNetworkTypeToString(networkType));
        }
        @Override
        public void onCallForwardingIndicatorChanged(boolean cfi) {
            text.append("\nCallForwardingIndicator:\t" + cfi);
        }
        @Override
        public void onDataActivity(int direction) {
            text.append("\nDataActivity:\t" +
                    convertDataActivityToString(direction));
        }
        @Override
        public void onDataConnectionStateChanged(int state) {
            text.append("\nDataConnectionState:\t" +
                    convertDataConnStateToString(state));
        }
        @Override
        public void onMessageWaitingIndicatorChanged(boolean mwi) {
            text.append("\nMessageWaitingIndicator:\t" + mwi);
        }
        @Override
        public void onServiceStateChanged(ServiceState serviceState) {
            text.append("\nService State:\t" +
                    convertServiceStateToString(serviceState.getState()));
        }
    };


public void onClickStart(){
    manager.listen(listener,
            PhoneStateListener.LISTEN_CALL_STATE |
                    PhoneStateListener.LISTEN_CALL_FORWARDING_INDICATOR |
                    PhoneStateListener.LISTEN_DATA_ACTIVITY |
                    PhoneStateListener.LISTEN_DATA_CONNECTION_STATE |
                    PhoneStateListener.LISTEN_MESSAGE_WAITING_INDICATOR |
                    PhoneStateListener.LISTEN_SERVICE_STATE);
    text.setText("Start phone info listener...");
}

public void onClickStop(){
    manager.listen(listener,
            PhoneStateListener.LISTEN_NONE);
    text.setText("Listener is stopped");
}


    private String convertServiceStateToString(int serviceState) {
        switch (serviceState) {
            case ServiceState.STATE_EMERGENCY_ONLY:
                return "Emergensy Only";
            case ServiceState.STATE_IN_SERVICE:
                return "In Service";

            case ServiceState.STATE_OUT_OF_SERVICE:
                return "Out of Service";
            case ServiceState.STATE_POWER_OFF:
                return "Power OFF";
            default:
                return "Not defined";
        }
    }



    private String convertCallStateToString(int callState) {
        switch(callState){
            case TelephonyManager.CALL_STATE_IDLE: return "IDLE";
            case TelephonyManager.CALL_STATE_OFFHOOK: return "OFFHOCK";
            case TelephonyManager.CALL_STATE_RINGING: return "RINGING";
            default: return "NOT DEFINED";
        }
    }

    private  String convertNetworkTypeToString(int networkType){
        switch (networkType){
            case TelephonyManager.NETWORK_TYPE_1xRTT: return "1xRTT";
            case TelephonyManager.NETWORK_TYPE_CDMA: return  "CDMA";
            case TelephonyManager.NETWORK_TYPE_EDGE: return "EDGE";
            case TelephonyManager.NETWORK_TYPE_EHRPD: return  "EHRPD";
            case TelephonyManager.NETWORK_TYPE_EVDO_0: return "EVDO_0";
            case TelephonyManager.NETWORK_TYPE_EVDO_A: return "EVDO_A";
            case TelephonyManager.NETWORK_TYPE_EVDO_B: return "EVDO_B";
            case TelephonyManager.NETWORK_TYPE_GPRS: return "GPRS";
            case TelephonyManager.NETWORK_TYPE_GSM: return "GSM";
            case TelephonyManager.NETWORK_TYPE_HSDPA: return "HSDPA";
            case TelephonyManager.NETWORK_TYPE_HSPA: return "HSPA";
            case TelephonyManager.NETWORK_TYPE_HSPAP: return "HSPAP";
            case TelephonyManager.NETWORK_TYPE_HSUPA: return "HSUPA";
            case TelephonyManager.NETWORK_TYPE_IDEN: return "IDEN";
            case TelephonyManager.NETWORK_TYPE_IWLAN: return "IWLAN";
            case TelephonyManager.NETWORK_TYPE_LTE: return "LTE";
            case TelephonyManager.NETWORK_TYPE_TD_SCDMA: return "TD SCDMA";
            case TelephonyManager.NETWORK_TYPE_UMTS: return "UMTS";
            case TelephonyManager.NETWORK_TYPE_UNKNOWN: return "UNKNOWN";
            default: return "NOT DEFINED";
        }
    }

    private String convertDataActivityToString(int dataActivity) {
        switch (dataActivity) {
            case TelephonyManager.DATA_ACTIVITY_DORMANT:
                return "Dormant";
            case TelephonyManager.DATA_ACTIVITY_IN:
                return "In";
            case TelephonyManager.DATA_ACTIVITY_INOUT:
                return "In-out";
            case TelephonyManager.DATA_ACTIVITY_NONE:
                return "None";
            case TelephonyManager.DATA_ACTIVITY_OUT:
                return "Out";
            default:
                return "Not defined";
        }
    }

    private String convertDataConnStateToString(int dataState) {
        switch (dataState) {
            case TelephonyManager.DATA_CONNECTED:
                return "Data connected";
            case TelephonyManager.DATA_CONNECTING:
                return "Data connecting";
            case TelephonyManager.DATA_DISCONNECTED:
                return "Data suspended";
            case TelephonyManager.DATA_SUSPENDED:
                return "Data suspended";
            default:
                return "Not defined";
        }
    }
}
