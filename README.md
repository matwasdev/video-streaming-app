# Video Streaming App


## Project Overview 📌
A video streaming application built with **Spring Boot**, **Spring Security**, and **Docker**.
Non-authenticated users can browse and watch videos.
Authenticated users can perform **CRUD operations** on comments, manage their profile, and save videos to their personal library.
The app also supports real-time **video streaming**, ensuring a smooth playback experience.


![image](https://github.com/user-attachments/assets/f4ae9d0a-6270-486b-b5c2-2b30935e1ed7)

## Configuration ⚙️
###  Setting up roles
To set up the roles for your application, make sure to run the following commands in your PostgreSQL container:

```
INSERT INTO roles (id, name, description) VALUES (1, 'USER', 'Standard user role');
INSERT INTO roles (id, name, description) VALUES (2, 'ADMIN', 'Administrator role');
```
### Adding Videos for Streaming 🎥
To enable video streaming:
- Create a folder named `videos` in your project root directory.
- Add your video files to that folder.
- Store the filenames in the `videos` table in your **PostgreSQL** database.

