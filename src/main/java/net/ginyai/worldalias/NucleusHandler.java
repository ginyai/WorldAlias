package net.ginyai.worldalias;

import io.github.nucleuspowered.nucleus.api.NucleusAPI;
import io.github.nucleuspowered.nucleus.api.exceptions.PluginAlreadyRegisteredException;
import io.github.nucleuspowered.nucleus.api.service.NucleusMessageTokenService;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.world.Locatable;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.Optional;

public class NucleusHandler implements NucleusMessageTokenService.TokenParser {
    private WorldAliasPlugin plugin;
    public NucleusHandler(WorldAliasPlugin plugin) throws PluginAlreadyRegisteredException {
        this.plugin = plugin;
        NucleusAPI.getMessageTokenService().register(plugin.getContainer(),this);
        if(!NucleusAPI.getMessageTokenService().registerPrimaryToken("worldalias",plugin.getContainer(),"alias")){
            plugin.getLogger().warn("Failed to register token '{{worldalias}}' to nucleus.");
        }
    }


    @Nonnull
    @Override
    public Optional<Text> parse(String tokenInput, CommandSource source, Map<String, Object> variables) {
        if(tokenInput.equals("alias")&&source instanceof Locatable){
            return Optional.ofNullable(plugin.getAlias(((Locatable) source).getWorld()));
        }
        return Optional.empty();
    }
}
