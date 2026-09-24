# Build APK bằng điện thoại

1. Tạo repository mới trên GitHub.
2. Upload toàn bộ nội dung project này vào repository (có thể upload ZIP rồi giải nén bằng Codespaces; cách dễ nhất là upload từng thư mục/file).
3. Commit vào nhánh `main`.
4. Vào tab **Actions**.
5. Chọn workflow **Build APK**.
6. Chờ job hoàn tất.
7. Mở workflow run vừa hoàn thành.
8. Ở cuối trang, phần **Artifacts**, tải `FootballLinksMVP-debug-apk`.
9. Giải nén artifact và cài `app-debug.apk` trên điện thoại.

Workflow dùng GitHub Actions, JDK 17 và Gradle 8.9; không cần Android Studio trên điện thoại.
