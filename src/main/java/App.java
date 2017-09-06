import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.List;
import java.util.ArrayList;
public class App{
  public static void main(String[] args){
    ProcessBuilder process = new ProcessBuilder();
       Integer port;

       // This tells our app that if Heroku sets a port for us, we need to use that port.
       // Otherwise, if they do not, continue using port 4567.

       if (process.environment().get("PORT") != null) {
           port = Integer.parseInt(process.environment().get("PORT"));
       } else {
           port = 4567;
       }

       setPort(port);
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";
    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("normalanimals", NormalAnimal.all());
      model.put("endangeredanimals", EndangeredAnimal.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
    post("/", (request, response) -> {
        Map<String, Object> model = new HashMap<String, Object>();
        String age = request.queryParams("age");
        if (age == null) {
          String name = request.queryParams("name");
          NormalAnimal newAnimal = new NormalAnimal(name);
          newAnimal.save();
        }else{
            String name = request.queryParams("name");
            String health = request.queryParams("health");
            EndangeredAnimal  newEndangeredAnimal = new EndangeredAnimal(name,age,health);
            newEndangeredAnimal.save();
        }
        model.put("normalanimals", NormalAnimal.all());
        model.put("endangeredanimals", EndangeredAnimal.all());
        model.put("template", "templates/index.vtl");
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());
      get("/normalanimals/:id", (request, response) -> {
        Map<String, Object> model = new HashMap<String, Object>();
        NormalAnimal animal =NormalAnimal.find(Integer.parseInt(request.params(":id")));
        model.put("sighting", Sighting.all());
        model.put("animal", animal);
        model.put("template", "templates/normalanimal.vtl");
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());
      get("/endangeredanimals/:id", (request, response) -> {
        Map<String, Object> model = new HashMap<String, Object>();
        EndangeredAnimal endangeredanimal =EndangeredAnimal.find(Integer.parseInt(request.params(":id")));
        model.put("sighting", Sighting.all());
        model.put("animal", endangeredanimal);
        model.put("template", "templates/endangeredanimal.vtl");
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());
      get("/sightings", (request, response) -> {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("sighting", Sighting.all());
        model.put("normalanimals", NormalAnimal.all());
        model.put("endangeredanimals", EndangeredAnimal.all());
        model.put("template", "templates/sightings.vtl");
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());
      post("/sightings", (request, response) -> {
          Map<String, Object> model = new HashMap<String, Object>();
          String location = request.queryParams("location");
          String rangerName = request.queryParams("rangerName");
          Integer animalId =Integer.parseInt(request.queryParams("animalId"));
          Sighting newSighting = new Sighting(animalId,location,rangerName);
          newSighting.save();
          model.put("template", "templates/index.vtl");
          return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());
  }
}
