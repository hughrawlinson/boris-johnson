package improbable.natures

import improbable.corelib.natures.{BaseNature, NatureApplication, NatureDescription}
import improbable.corelibrary.rigidbody.RigidbodyNature
import improbable.corelibrary.transforms.TransformNature
import improbable.math.Coordinates
import improbable.papi.entity.behaviour.EntityBehaviourDescriptor
import improbable.util.EntityPrefabs.BOT

object BotNature extends NatureDescription {

  override def dependencies = Set[NatureDescription](BaseNature, TransformNature, RigidbodyNature, ColoredNature)

  override def activeBehaviours: Set[EntityBehaviourDescriptor] = Set.empty

  def apply(initialPosition: Coordinates): NatureApplication = {
    application(
      natures = Seq(
        BaseNature(entityPrefab = BOT),
        TransformNature(initialPosition.toVector3d),
        RigidbodyNature(drag = 0.2f),
        ColoredNature(color = java.awt.Color.white))
    )
  }

}
