#Testing controllers in ASP.NET MVC (Web API 2) solution

When working with Web API 2 applications, there is usually a data access layer(DAL) involved.
Most often it is created using Entity Framework (EF), but it could also be a custom implementation.
Focus here will be on the case where EF is used as a choice for DAL.
In this case, controllers usually have a reference to a ```DbContext``` class that contains various ```DbSets``` that map to tables in the database.
To be able to unit-test controller logic, we would require a working database.
This can be achieved by using a different connection string that will target test database.
While this is valid approach for some purposes (UI tests, end to end tests) it lacks one of the characteristics of a true unit test ```Runs in memory (no DB or File Access)```

To overcome this problem, instead of using specifict ```DbContext```, we can create our own interface and make our database context inherit from DbContext and additionally implement our interface and in the end inject it via constructor.
But now, there is another problem, because Web API creates the controller when it routes the request, and Web API doesn't know anything about our interface and the concrete implementation of that interface. This is where the Web API dependency resolver comes in.
In our sample, we use UnityResolver that relies on UnityContainer which is part of Unity nuget package.

To see how to implement this interface and how to fake a DbSet and a DbContext, you can check the sample in ```WebApi2 Sample``` folder.