package pl.juniorjavaready;

public interface SampleJobOfferResponse {

    default String bodyWithZeroOffersJson() {
        return "[]";
    }

    default String bodyWithFourOffersJson() {
        return """
    [
        {
            "id": 1,
            "title": "Java Developer",
            "company": "TechCorp",
            "location": "Warszawa",
            "salary": "12000 PLN"
        },
        {
            "id": 2,
            "title": "Frontend Developer",
            "company": "WebSolutions",
            "location": "Kraków",
            "salary": "10000 PLN"
        },
        {
            "id": 3,
            "title": "Data Scientist",
            "company": "AI Innovators",
            "location": "Wrocław",
            "salary": "14000 PLN"
        },
        {
            "id": 4,
            "title": "DevOps Engineer",
            "company": "CloudTech",
            "location": "Gdańsk",
            "salary": "13000 PLN"
        }
    ]
    """;
    }
}
