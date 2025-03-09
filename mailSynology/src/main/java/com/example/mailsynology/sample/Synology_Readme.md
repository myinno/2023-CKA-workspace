## 문제 해결 팁

인증 실패
- MailPlus 관리자 센터 > SMTP 설정에서 SMTP 인증 활성화 확인

SSL 오류
- NAS의 SSL 인증서를 Java truststore에 추가

```bash
bash
keytool -importcert -alias synology -file /path/to/nas.crt -keystore $JAVA_HOME/lib/security/cacerts
```

포트 차단
- 방화벽에서 아웃바운드 587/TCP 포트 허용 확인

로그 확인
- MailPlus Server의 로그 센터에서 상세 오류 메시지 확인

### 고급 설정

HTML 형식 메일 발송 시:

```java
message.setContent("<h1>HTML 내용</h1>", "text/html; charset=utf-8");

```

첨부 파일 추가:

```java
MimeBodyPart attachmentPart = new MimeBodyPart();
attachmentPart.attachFile(new File("path/to/file.pdf"));
```

대량 메일 발송 시 세션 타임아웃 설정:

```java
props.put("mail.smtp.connectiontimeout", "5000");
props.put("mail.smtp.timeout", "5000");
```

참고 사항
1. 보안을 위해 자격 증명은 환경 변수나 구성 파일에서 관리
2. 실제 프로덕션 환경에서는 SSL 인증서 검증 활성화 권장
3. MailPlus의 일일 발송 제한 설정 확인 (기본값: 계정당 200건/일)

이 코드를 실행하기 전에 반드시 실제 환경에 맞게 다음 항목을 수정해야 합니다:
- SMTP 호스트 주소
- 포트 번호 (SSL 사용시 465)
- 사용자 인증 정보
- 수신자 이메일 주소