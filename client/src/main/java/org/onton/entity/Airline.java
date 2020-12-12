package org.onton.entity;

import java.io.Serializable;

public class Airline implements Serializable {

        private int id;
        private String name;

        public Airline() {
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {  return name; }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
}
