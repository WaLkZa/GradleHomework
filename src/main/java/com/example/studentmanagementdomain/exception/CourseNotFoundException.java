package com.example.studentmanagementdomain.exception;

import com.example.studentmanagementdomain.util.Constant;

public class CourseNotFoundException extends CustomRuntimeException{
    public CourseNotFoundException() {
        super(Constant.COURSE_NOT_EXISTS);
    }
}
