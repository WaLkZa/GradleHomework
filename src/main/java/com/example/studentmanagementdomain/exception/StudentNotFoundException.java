package com.example.studentmanagementdomain.exception;

import com.example.studentmanagementdomain.util.Constant;

public class StudentNotFoundException extends CustomRuntimeException {
    public StudentNotFoundException() {
        super(Constant.STUDENT_NOT_EXISTS);
    }
}
