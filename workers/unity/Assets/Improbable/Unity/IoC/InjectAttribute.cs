using System;

namespace IoC
{
	[AttributeUsage(AttributeTargets.Property | AttributeTargets.Field, AllowMultiple = false)]
	public class InjectAttribute: Attribute
	{
	}
}
