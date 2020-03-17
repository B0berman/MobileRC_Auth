package com.mobilerc.registration.helpers

import java.util.*
import javax.activation.DataHandler
import javax.activation.DataSource
import javax.activation.FileDataSource
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeBodyPart
import javax.mail.internet.MimeMessage
import javax.mail.internet.MimeMultipart


fun sendEmail(to: String, subject: String, body: String, attachment: String?, aClass: Class<*>) : Boolean {
    val properties = Properties()
    properties["mail.smtp.auth"] = "true"
    properties["mail.smtp.starttls.enable"] = "true"
    properties["mail.smtp.host"] = "smtp.gmail.com"
    properties["mail.smtp.port"] = "587"
    properties["mail.smtp.ssl.trust"] = "smtp.gmail.com"

    val mail = getProperty("taleo_mail", aClass, "keys")
    val session = Session.getInstance(properties, object : Authenticator() {
        override fun getPasswordAuthentication(): PasswordAuthentication {
            return PasswordAuthentication(mail, getProperty("taleo_password", aClass, "keys"))
        }
    })

    try {
        val message = MimeMessage(session)
        message.setFrom(InternetAddress("$mail@gmail.com"))
        message.setRecipients(
                Message.RecipientType.TO, InternetAddress.parse(to)
        )
        message.subject = subject

        val messageBodyPart = MimeBodyPart()
        messageBodyPart.setContent(body, "text/html")

        val multipart = MimeMultipart()
        multipart.addBodyPart(messageBodyPart)

        // Part two is attachment
        if (attachment != null) {
            val attachmentBodyPart = MimeBodyPart()
            val source: DataSource = FileDataSource(attachment)
            attachmentBodyPart.dataHandler = DataHandler(source)
            attachmentBodyPart.fileName = "Accident.pdf"
            multipart.addBodyPart(attachmentBodyPart)
        }

        message.setContent(multipart)
        Transport.send(message)
        return true
    } catch (mex: MessagingException) {
        mex.printStackTrace()
        return false
    }
}
