package c22376553;
import MusicVis.MyVisual;

public class Snowflake {
    MyVisual mv;
    PenroseLSystem ps;
    int width;
    int height;
    public Snowflake(MyVisual mv) {
        this.mv = mv;
        ps = new PenroseLSystem(mv); // Pass mv to PenroseSnowflakeLSystem constructor
        height = mv.height;
        width = mv.width;
    }

    public void render() {

      mv.hint(mv.DISABLE_DEPTH_TEST);
      mv.pushMatrix();  
      ps.simulate(4);
      mv.strokeWeight(4);
      ps.render();
      mv.popMatrix();
      mv.hint(mv.ENABLE_DEPTH_TEST);
      ps.reset(); // Reset PenroseLSystem

    }

class LSystem
{
  int steps = 0;

  String axiom;
  String rule;
  String production;

  float startLength;
  float drawLength;
  float theta;

  int generations;
  MyVisual mv;

  LSystem(MyVisual mv) {
    this.mv = mv;
    axiom = "F";
    rule = "F+F-F";
    startLength = 380.0f;
    theta = mv.radians(120.0f);
    reset();
  }

  void reset() {
    production = axiom;
    drawLength = startLength;
    generations = 0;
  }

  int getAge() {
    return generations;
  }


void render() {
    mv.translate(width / 2, height / 2); // Translate to the bottom center of the screen
    steps += 20;          
    if (steps > production.length()) {
      steps = production.length();
    }
    for (int i = 0; i < steps; i++) {
      char step = production.charAt(i);
      if (step == 'F') {
        mv.rect(0, 0, -drawLength, -drawLength);
        mv.noFill();
        mv.translate(0, -drawLength);
      } 
      else if (step == '+') {
        mv.rotate(theta);
      } 
      else if (step == '-') {
        mv.rotate(-theta);
      } 
      else if (step == '[') {
        mv.pushMatrix();
      } 
      else if (step == ']') {
        mv.popMatrix();            
      }
    }
  }


  void simulate(int gen) {
    while (getAge() < gen) {
      production = iterate(production, rule);
    }
  }

  String iterate(String prod_, String rule_) {
    drawLength = drawLength * 0.6f;
    generations++;
    String newProduction = prod_;          
    newProduction = newProduction.replaceAll("F", rule_);
    return newProduction;
  }
}


 class PenroseLSystem extends LSystem {

  int steps = 0;
  float somestep = (float)0.1;
  String ruleW;
  String ruleX;
  String ruleY;
  String ruleZ;
  MyVisual mv;
  int lastColorChange = 0;
  int colorChangeInterval = 1500;
  int currentColor, nextColor;

        PenroseLSystem(MyVisual mv) {
            super(mv);
            this.mv = mv;
            axiom = "[X]++[X]++[X]++[X]++[X]";
            ruleW = "YF++ZF4-XF[-YF4-WF]++";
            ruleX = "+YF--ZF[3-WF--XF]+";
            ruleY = "-WF++XF[+++YF++ZF]-";
            ruleZ = "--YF++++WF[+ZF++++XF]--XF";
            startLength = 2400f;
            theta = mv.radians(36);
            reset();
        }

  void useRule(String r_) {
    rule = r_;
  }

  void useAxiom(String a_) {
    axiom = a_;
  }

  void useLength(float l_) {
    startLength = l_;
  }

  void useTheta(float t_) {
    theta = mv.radians(t_);
  }

  void reset() {
    production = axiom;
    drawLength = startLength;
    generations = 0;
  }

  int getAge() {
    return generations;
  }

  void render() {
    if (mv.millis() - lastColorChange > colorChangeInterval) {
        currentColor = nextColor;
        nextColor = mv.color(mv.random(70,500), mv.random(70,500), mv.random(70,500));
        lastColorChange = mv.millis();
    }

    float amt = (float)(mv.millis() - lastColorChange) / colorChangeInterval;
    mv.translate(width/2, height/2);
    int pushes = 0;
    int repeats = 5;
    steps += 20;          
    if (steps > production.length()) {
      steps = production.length();
    }

    for (int i = 0; i < steps; i++) {
      char step = production.charAt(i);
      if (step == 'F') {
        mv.stroke(mv.lerpColor(currentColor, nextColor, amt), 60);
        for (int j = 0; j < repeats; j++) {
          mv.line(0, 0, 0, -drawLength);
          mv.noFill();
          mv.translate(0, -drawLength);
        }
        repeats = 1;
      } 
      else if (step == '+') {
        for (int j = 0; j < repeats; j++) {
          mv.rotate(theta);
        }
        repeats = 1;
      } 
      else if (step == '-') {
        for (int j =0; j < repeats; j++) {
          mv.rotate(-theta);
        }
        repeats = 1;
      } 
      else if (step == '[') {
        pushes++;            
        mv.pushMatrix();
      } 
      else if (step == ']') {
        mv.popMatrix();
        pushes--;
      } 
      else if ( (step >= 48) && (step <= 57) ) {
        repeats = (int)step - 48;
      }
    }

    // Unpush if we need too
    while (pushes > 0) {
      mv.popMatrix();
      pushes--;
    }
  }

  String iterate(String prod_, String rule_) {
    String newProduction = "";
    for (int i = 0; i < prod_.length(); i++) {
      char step = production.charAt(i);
      if (step == 'W') {
        newProduction = newProduction + ruleW;
      } 
      else if (step == 'X') {
        newProduction = newProduction + ruleX;
      }
      else if (step == 'Y') {
        newProduction = newProduction + ruleY;
      }  
      else if (step == 'Z') {
        newProduction = newProduction + ruleZ;
      } 
      else {
        if (step != 'F') {
          newProduction = newProduction + step;
        }
      }
    }

    drawLength = drawLength * 0.5f;
    generations++;
    return newProduction;
  }

}
}
