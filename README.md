# Overview
This a simple example of how to send a mail with Java Mail.

# Settings
Open JavaMailApp.java file and set the constants:

* USERNAME: the gmail user account, who will send the email.
* PASSWORD: if your account uses 2-factors then you need to generate an App password (see https://support.google.com/accounts/answer/185833?hl=en).  
* FROM: the expeditor, but it will NOT send the email.
* TO: the list of the receivers, but they will NOT receive the email. (the list of emails is separated by ",")
* RCPT_TO: the list of the recipients, to whom will the email be delivered. (the list of emails is separated by ",")


# Sources
https://www.baeldung.com/java-email