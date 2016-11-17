# Software Security

## Introduction

86% percent of all websites have at least one serious vulnerability, and most of the time, they contain more than one, according to WhiteHat Security's “[2015 Website Security Statistics Report](https://info.whitehatsec.com/rs/whitehatsecurity/images/2015-Stats-Report.pdf).”

Our goal with this on-boarding is to mitigate risk of attacks by exploiting vulnerabilities in the software systems our engineers create.

### What is Software Security?

First let u look at Information Security.

The goal of Information Security is to provide the "CIA triad":
* **Confidentiality** - prevents disclosure of sensitive information
* **Integrity** - maintains the consistency, accuracy, and trustworthiness of data
* **Availability** - guarantees reliable access to the information by authorized people

It also provides other services, like:
* **Authentication** - uniquely identifying an individual
* **Authorization** - ensures rights, privileges, or permissions that can only be performed by a certain individual
* **Non-repudiation** - guarantees associating actions or changes to a unique individual without possibility of denying those actions

Software Security provides these services on the application layer, which is the layer we mostly work as software engineers.

### Cryptography

Cryptographic systems provide help in providing all services except authorization.

Some concepts in cryptography are:

#### Symmetric Cryptography
Symmetric Cryptography is the most traditional form of cryptography. In a symmetric cryptosystem, the involved parties share a common secret (password, pass phrase, or key). Data is encrypted and decrypted using the same key.

#### Asymmetric Cryptography (also called Public/Private Key Cryptography)
Asymmetric algorithms use two keys, one to encrypt the data, and either key to decrypt. These inter-dependent keys are generated together. One is labeled the Public key and is distributed freely. The other is labeled the Private Key and must be kept hidden.

PGP and SSL are prime examples of a systems implementing asymmetric cryptography, using RSA or other algorithms.

If interested read more on cryptography here: [https://www.owasp.org/index.php/Guide_to_Cryptography](https://www.owasp.org/index.php/Guide_to_Cryptography)

### Privacy

Another important topic is privacy. This means mishandling private information, such as customer passwords or social security numbers, which can compromise user privacy, and is often illegal.

Security and privacy concerns often seem to compete with each other. From a security perspective, you should record all important operations so that any anomalous activity can later be identified. However, when private data is involved, this practice can in fact create risk.

To read more on cryptography please visit: [https://www.owasp.org/index.php/Privacy_Violation](https://www.owasp.org/index.php/Privacy_Violation)