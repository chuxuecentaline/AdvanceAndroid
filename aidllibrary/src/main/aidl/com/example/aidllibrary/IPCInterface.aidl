// IPCInterface.aidl
package com.example.aidllibrary;

// Declare any non-default types here with import statements

interface IPCInterface {

    void send(String aString);
    String getContent();
}
