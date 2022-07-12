package com.example.studentmanagementdomain.exception;

import com.example.studentmanagementdomain.util.Constant;

public class TeacherNotFoundException extends CustomRuntimeException {
    public TeacherNotFoundException() {
        super(Constant.TEACHER_NOT_EXISTS);
    }
}
