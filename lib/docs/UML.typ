#import "@preview/cetz:0.3.0": *

#cetz.canvas({
  import cetz.draw: *

  // Clase Persona
  rect((0, 0), (4, 3))
  text((2, 2.5), "Persona", anchor: "n")

  line((0, 2.1), (4, 2.1))
  text((0.2, 1.8), "+ nombre: String", anchor: "w")
  text((0.2, 1.4), "+ edad: Int", anchor: "w")

  line((0, 1.1), (4, 1.1))
  text((0.2, 0.7), "+ saludar(): Void", anchor: "w")
})

