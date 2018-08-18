package net.ginyai.worldalias;

import me.rojo8399.placeholderapi.Placeholder;
import me.rojo8399.placeholderapi.PlaceholderService;
import me.rojo8399.placeholderapi.Source;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.world.Locatable;

public class PapiHandler {
    private WorldAliasPlugin plugin;

    public PapiHandler(WorldAliasPlugin plugin) throws Exception {
        this.plugin = plugin;
        PlaceholderService service = Sponge.getServiceManager().provideUnchecked(PlaceholderService.class);
        service.load(this,"worldalias",plugin)
                .author("GiNYAi")
                .description("A custom world alias placeholder.")
                .buildAndRegister();
    }

    @Placeholder(id = "worldalias")
    public Text getWorldAlias(@Source Locatable source){
        return plugin.getAlias(source.getWorld());
    }
}
