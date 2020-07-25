package com.example.vymoassignment.util;

public interface Resource<T> {
    class Success<T> implements Resource<T> {
        T data;

        public Success(T data) {
            this.data = data;
        }

        public T getData() {
            return data;
        }
    }

    class Error<T> implements Resource<T> {
        String message;

        public Error(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    class Loading<T> implements Resource<T> {
    }
}

