<html>
<head>
    <title>Fruit Picker</title>
</head>
<body>
    <h1>Fruit Picker</h1>
    <form action="/favorite_fruit" method="post">
        <p>What is your favorite fruit?</p>
        <#list fruits as fruit>
            <p>
                <input type="radio" name="fruit" id="${fruit_index}" value="${fruit}">
                <label for="${fruit_index}">${fruit}</label>
            </p>
        </#list>
        <input type="submit" value="Submit" />
    </form>
</body>
</html>