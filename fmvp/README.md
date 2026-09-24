# Football Links MVP

MVP Android app bằng Kotlin + Jetpack Compose.

## Có sẵn
- Danh sách trận đấu mẫu.
- Lọc theo giải.
- Hiển thị giờ thi đấu.
- Nút mở link phát sóng.
- Cấu trúc dễ mở rộng thành API/backend.

## Chạy
Mở thư mục này bằng Android Studio (bản có Android Gradle Plugin 8.7.x), đồng bộ Gradle và Run trên emulator/thiết bị Android.

## Bước tiếp theo
Thay `sampleMatches` bằng API backend. Backend nên trả về:
- id
- giải đấu
- đội nhà/đội khách
- kickoff UTC
- trạng thái trận
- danh sách broadcaster/link được phép

Không đưa các link phát sóng không có quyền vào dữ liệu ứng dụng.
