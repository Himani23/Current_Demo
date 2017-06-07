package com.aptech.current_demo;

/**
 * Created by Vishal singh rajput on 5/11/2017.
 */

public class DownCourse {

        String subject;
        String filename;
        String filepath;

        public DownCourse(String subject, String filename, String filepath) {
            this.subject = subject;
            this.filename = filename;
            this.filepath = filepath;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getFilename() {
            return filename;
        }

        public void setFilename(String filename) {
            this.filename = filename;
        }

        public String getFilepath() {
            return filepath;
        }

        public void setFilepath(String filepath) {
            this.filepath = filepath;
        }
    }

