package improbable.behaviours.color

import improbable.papi.entity.{Entity, EntityBehaviour}
import improbable.physical.{Fire, FireWriter}
import java.awt.color.ColorSpace
import java.awt.Color

class SetColorFromFireBehaviour(fire: FireWriter, colorInterface : ColorInterface, entity : Entity) extends EntityBehaviour{
  var xyzspace = ColorSpace.getInstance(ColorSpace.CS_CIEXYZ)
  var rgbspace = ColorSpace.getInstance(ColorSpace.CS_CIEXYZ)
  var bcol = xyzspace.fromRGB(Color.blue.getColorComponents(null))
  var rcol = xyzspace.fromRGB(Color.red.getColorComponents(null))

  entity.watch[Fire].bind.onFire {
    onFire => {
      if (onFire) {
        entity.watch[Fire].bind.temperature {
          f => {
            val col = rgbspace.fromCIEXYZ(Array(
              bcol(0) + (rcol(0) - bcol(0)) * ((f - 32F) / 100F),
              bcol(1) + (rcol(1) - bcol(1)) * ((f - 32F) / 100F),
              bcol(2) + (rcol(2) - bcol(2)) * ((f - 32F) / 100F)
            ))
            colorInterface.setColor(new Color(col(0),col(1),col(2)))
          }
        }
      }
    }
  }
}