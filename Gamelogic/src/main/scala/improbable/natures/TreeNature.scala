package improbable.natures

import improbable.corelib.natures.{NatureApplication, StaticBodyEntity, NatureDescription}
import improbable.math.Coordinates
import improbable.util.EntityPrefabs.TREE

object TreeNature extends NatureDescription {

  override val dependencies = Set[NatureDescription](StaticBodyEntity)

  override def activeBehaviours = {
    Set()
  }

  def apply(initialPosition: Coordinates): NatureApplication = {
    application(
      natures = Seq(
        StaticBodyEntity(prefab = TREE, position = initialPosition)
      )
    )
  }

}
