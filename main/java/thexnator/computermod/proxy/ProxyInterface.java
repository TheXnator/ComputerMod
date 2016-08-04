package thexnator.computermod.proxy;

public interface ProxyInterface 
{
	
	public void preInit();
	
	public boolean isSinglePlayer();

	public boolean isDedicatedServer();

	public void init();
	
}
