package net.alluysl.yeetuselessmenubuttons;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screen.v1.Screens;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.minecraft.client.gui.widget.AbstractButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

public class YeetUselessMenuButtons implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		ScreenEvents.AFTER_INIT.register((client, screen, scaledWidth, scaledHeight) -> {
			if (screen instanceof GameMenuScreen) {
				// If you want to create a button that's how you do it (with a few imports) for the record, I'll keep it there as a comment because WHY NOT
//				Screens.getButtons(screen).add(new ButtonWidget(0, 0, 256, 20, new LiteralText("test"), new ButtonWidget.PressAction() {
//					@Override
//					public void onPress(ButtonWidget button) {
//						// don't do anything
//					}
//				}));

				// This is the brute method, that requires the indices to be changed everytime the menu changes or a mod adds buttons above (straight up removing the buttons makes the Mod Menu mod very sad by the way)
//				Screens.getButtons(screen).get(3).visible = false;
//				Screens.getButtons(screen).get(4).visible = false;

				// The cleaner method that TerraformersMC's Mod Menu uses, credit is due etc etc
				for (AbstractButtonWidget button : Screens.getButtons(screen))
					if (buttonHasText(button, "menu.sendFeedback") || buttonHasText(button, "menu.reportBugs"))
						button.visible = false;
			}
		});
	}

	// Shamelessly stolen from TerraformersMC's Mod Menu :>
	private static boolean buttonHasText(AbstractButtonWidget button, String translationKey) {
		Text text = button.getMessage();
		return text instanceof TranslatableText && ((TranslatableText) text).getKey().equals(translationKey);
	}
}
