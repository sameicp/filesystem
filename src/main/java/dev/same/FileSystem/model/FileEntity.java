package dev.same.FileSystem.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="files")
public class FileEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        private String name;
        private String type;
        private long size;
        @Column(columnDefinition = "LONGBLOB")
        private byte[] data;
        @Temporal(TemporalType.DATE)
        private Date uploadedAt;

        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

        public Date getUploadedAt() {
                return uploadedAt;
        }

        public void setUploadedAt(Date uploadedAt) {
                this.uploadedAt = uploadedAt;
        }

        public byte[] getData() {
                return data;
        }

        public void setData(byte[] data) {
                this.data = data;
        }

        public long getSize() {
                return size;
        }

        public void setSize(long size) {
                this.size = size;
        }

        public String getType() {
                return type;
        }

        public void setType(String type) {
                this.type = type;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }
}
