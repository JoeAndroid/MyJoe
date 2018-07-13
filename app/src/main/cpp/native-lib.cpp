//
// Created by 乔兵 on 2018/7/9.
//
#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jint JNICALL
Java_com_example_joe_MyMainActivity_sum(JNIEnv *env, jclass type, jint a, jint b) {

    // TODO
    jint sum = a + b;
    return sum;

}