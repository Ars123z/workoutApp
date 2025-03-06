package com.example.greatworkouts.data

data class TermsOfServiceData(
    val heading: String,
    val body: String
)

val termsOfServiceData: List<TermsOfServiceData> = listOf(
    TermsOfServiceData(
        heading= "Terms of Service for Great Workouts",
        body= "Welcome to Great Workouts, a fitness platform provided by NewGenTech (\"we,\" \"us,\" or \"our\"). These Terms of Service (\"Terms\") govern your access to and use of the App, including all features, content, and services offered through it (collectively, the \"Service\"). By downloading, accessing, or using the App, you (\"you\" or \"User\") agree to be bound by these Terms. If you do not agree to these Terms, please do not use the Service."
    ),
    TermsOfServiceData(
        heading= "1. Acceptance of Terms",
        body= "By creating an account, installing the App, or otherwise engaging with the Service, you confirm that you have read, understood, and agree to these Terms, as well as our Privacy Policy (linked [insert link]), which is incorporated into these Terms by reference. We reserve the right to update or modify these Terms at any time, and such changes will be effective upon posting to the App or our website. Your continued use of the Service after such changes constitutes acceptance of the updated Terms."
    ),
    TermsOfServiceData(
        heading= "2. Eligibility",
        body= "Be at least 13 years of age (or the minimum age required in your jurisdiction to use online services without parental consent).\n" +
                "If between 13 and 18 (or the age of majority in your jurisdiction), have parental or guardian consent to use the Service.\n" +
                "Not be barred from using the Service under applicable law or prior termination of your account by us."
    ),
    TermsOfServiceData(
        heading="Account Registration and Security",
        body="3.1 Account Creation: To access certain features of the Service, you may need to create an account. You agree to provide accurate, current, and complete information during registration and to update it as necessary.\n" +
        "3.2 Account Responsibility: You are responsible for maintaining the confidentiality of your account credentials (e.g., username and password) and for all activities that occur under your account. Notify us immediately at [insert contact email] if you suspect unauthorized use of your account.\n" +
        "3.3 Termination of Account: We reserve the right to suspend or terminate your account at our discretion, including for violations of these Terms, fraudulent activity, or inactivity exceeding [insert time period, e.g., 12 months]."
    ),
    TermsOfServiceData(
        heading="4. Use of the Service",
        body= "4.1 License: Subject to your compliance with these Terms, we grant you a limited, non-exclusive, non-transferable, revocable license to use the App for personal, non-commercial purposes.\n" +
        "4.2 Permitted Use: You may use the Service to access fitness plans, track workouts, monitor progress, and engage with other features we provide.\n" +
        "4.3 Prohibited Conduct: You agree not to:\n" +
        "Use the Service for any illegal, harmful, or unauthorized purpose.\n" +
                "Modify, reverse-engineer, or attempt to access the source code of the App.\n" +
                "Distribute, sell, or sublicense the Service or any content provided through it.\n" +
                "Post or transmit harmful code, viruses, or malware.\n" +
                "Harass, abuse, or infringe on the rights of other users.\n" +
                "Misrepresent your identity or provide false information.\n" +
                "Use automated tools (e.g., bots, scrapers) to access or collect data from the Service without our consent."
    ),
    TermsOfServiceData(
        heading= "5. Health and Safety Disclaimer",
        body= "5.1 Not Medical Advice: The Service provides fitness and wellness information and tools for general informational purposes only. It is not a substitute for professional medical advice, diagnosis, or treatment. Consult a qualified healthcare provider before starting any fitness program or making changes to your diet or exercise routine.\n" +
        "5.2 Assumption of Risk: You acknowledge that physical exercise involves inherent risks, including injury or illness. You use the Service at your own risk and agree that we are not liable for any harm resulting from your use of the App or its content.\n" +
        "5.3 User Responsibility: You are responsible for ensuring that any fitness activities you undertake through the Service are appropriate for your health, fitness level, and physical condition."
    ),
    TermsOfServiceData(
        heading= "6. Subscriptions and Payments",
        body= "6.1 Subscription Plans: Certain features of the Service may require a paid subscription. Available plans, pricing, and features will be detailed within the App.\n" +
        "6.2 Payment Terms: Subscriptions are billed in advance on a recurring basis (e.g., monthly or annually). You authorize us to charge your selected payment method for the applicable fees.\n" +
        "6.3 Auto-Renewal: Subscriptions automatically renew unless canceled at least [insert time, e.g., 24 hours] before the renewal date. You may cancel your subscription through your account settings or by contacting us at newgentech@gmail.com.\n" +
        "6.4 Refunds: All payments are non-refundable except as required by applicable law or as explicitly stated in a separate refund policy.\n" +
        "6.5 Price Changes: We may adjust subscription fees at any time. You will be notified of changes in advance, and continued use after the change constitutes acceptance of the new pricing."
    ),
    TermsOfServiceData(
        heading= "7. Intellectual Property Rights",
        body= "8.1 Our Content: All content provided by us (e.g., workout plans, videos, text, logos) is owned by or licensed to [Your Company Name] and protected by copyright, trademark, and other laws.\n" +
        "8.2 Restrictions: You may not reproduce, distribute, or create derivative works from our content without prior written permission, except as permitted under these Terms."
    ),
    TermsOfServiceData(
        heading= "9. Third-Party Links and Services",
        body= "The Service may contain links to third-party websites, apps, or services (e.g., payment processors, fitness trackers). We are not responsible for the availability, accuracy, or practices of these third parties, and their inclusion does not imply endorsement."
    ),
    TermsOfServiceData(
        heading= "10. Termination",
        body= "10.1 By You: You may stop using the Service and delete your account at any time.\n" +
        "10.2 By Us: We may suspend or terminate your access to the Service for any reason, including violation of these Terms, with or without notice.\n" +
        "10.3 Effect of Termination: Upon termination, your license to use the Service ends, and we may delete your account data, subject to applicable law."
    ),
    TermsOfServiceData(
        heading= "11. Limitation of Liability",
        body= "The Service is provided \"as is\" and \"as available\" without warranties of any kind, express or implied (e.g., fitness for a particular purpose, accuracy).\n" +
        "We are not liable for any indirect, incidental, special, or consequential damages (e.g., loss of data, profits, or injuries) arising from your use of the Service.\n" +
        "Our total liability to you for any claim will not exceed the amount you paid us in the preceding 12 months, or \$100, whichever is greater."
    ),
    TermsOfServiceData(
        heading= "12. Indemnification",
        body= "You agree to indemnify and hold [Your Company Name], its affiliates, officers, employees, and agents harmless from any claims, damages, or losses arising from your use of the Service, violation of these Terms, or infringement of any third-party rights."
    )
)