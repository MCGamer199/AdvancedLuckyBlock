package com.LuckyBlock.Engine;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;

public class MessagesLoader {
	
	
	public static void loadMessages(FileConfiguration file, File fileF){
	file.options().header("All Messages");
	file.addDefault("NoPermission", "&cYou don't have permission for this command!");
	file.addDefault("NoPermission1", "&cYou don't have permission!");
	file.addDefault("InvalidArgs", "&4Error: invalid arguments!");
	file.addDefault("InvalidSender", "&cError: invalid sender!");
	file.addDefault("InvalidCommand", "&cInvalid Command. Try &a/<lbcmd> help &cfor list of commands.");
	file.addDefault("InvalidCommand1", "&cInvalid Command. Try &a/<lbwcmd> help &cfor list of commands.");
	file.addDefault("InvalidCommand2", "&cInvalid Command. try &a/<tcmd> help &cfor list of commands.");
	file.addDefault("InvalidPlayer", "&6%Target% &cdoes not exist!");
	file.addDefault("InvalidNumber", "&cError: Invalid Number!");
	file.addDefault("InvalidGame", "&cInvalid Game!");
	file.addDefault("InvalidTeam", "&cInvalid Team!");
	file.addDefault("NoLB", "&cNo LuckyBlock found!");
	file.addDefault("UnknownError", "&cAn unknown error has occured!");
	file.addDefault("SetLuck.Success", "&aSetting Luck to &6%Luck%");
	file.addDefault("SetLuck.InvalidUsage", "&c/<lbcmd> setluck &a(Luck)");
	file.addDefault("SetLuck.InvalidBlock", "&cError: Invalid Block!");
	file.addDefault("SetChance.Success", "&aSetting Chance to &6%Chance%");
	file.addDefault("SetChance.InvalidUsage", "&c/<lbcmd> setchance &a(Chance)");
	file.addDefault("SetChance.InvalidBlock", "&cError: Invalid Block!");
	file.addDefault("SetOwner.Success", "&aSetting Lucky Block's Owner to &6%Owner%");
	file.addDefault("SetOwner.InvalidUsage", "&c/<lbcmd> setowner &a(UUID)");
	file.addDefault("SetOwner.NotLuckyBlock", "&cThe Targeted Block isn't a Lucky Block!");
	file.addDefault("SetOwner.InvalidBlock", "&cCan't Set Block's Owner to &6%BlockType%");
	file.addDefault("Gift.Success", "&aOpened a Gift!");
	file.addDefault("Gift.NoGifts.Sender", "&cYou don't have any gift!");
	file.addDefault("Gift.NoGifts.Target", "&6%Target% &cdoes not have any gift!");
	file.addDefault("Reload", "&aReloaded config.yml!");
	file.addDefault("RemoveLuckyBlocks.Success", "&aRemoved all Lucky Blocks!");
	file.addDefault("RemoveLuckyBlocks.NoLuckyBlock", "&cNo Lucky Block Found!");
	file.addDefault("LeaveGame.Success.Player", "&aTeleporting to main lobby!");
	file.addDefault("LeaveGame.Success.Players.1", "&6%Player% &5Left the Game!");
	file.addDefault("LeaveGame.Success.Players.2", "&6%PlayersLeft% &ePlayers left!");
	file.addDefault("LeaveGame.Fail", "&cYou are not in a game!");
	file.addDefault("SetMainLobby.Success", "&aSetting main lobby!");
	file.addDefault("SetMainLobby.InvalidUsage", "&c/<lbwcmd> setmainlobby &6world x y z");
	file.addDefault("SetLobby.Success", "&aSetting Lobby for &6ID:%ID%");
	file.addDefault("SetLobby.InvalidUsage", "&c/<lbwcmd> setlobby &a[ID]");
	file.addDefault("SetMaxPlayers.Success", "&aSetting maxplayers to &5%MaxPlayers% &aFor &6ID:%ID%");
	file.addDefault("SetMaxPlayers.InvalidUsage", "&c/<lbwcmd> setmaxplayers &a[ID] (Number)");
	file.addDefault("Lobby.Success.1", "&aTeleporting to main lobby!");
	file.addDefault("Lobby.Success.2", "&aTeleporting &6%Target% &ato main lobby!");
	file.addDefault("Lobby.InvalidLobby", "&cError: Invalid Location! you have to set new location.");
	file.addDefault("RemoveGame.Success", "&aRemoved &6ID:%ID%");
	file.addDefault("RemoveGame.InvalidUsage", "&c/<lbwcmd> removegame &a[ID]");
	file.addDefault("ClearGames.Success", "&aRemoved &6%Count% Games!");
	file.addDefault("ClearGames.NoGame", "&cNo Game Found!");
	file.addDefault("SetSpawn.Success", "&aSetting Spawn &5%Number% &afor &6ID:%ID%");
	file.addDefault("SetSpawn.InvalidUsage", "&c/<lbwcmd> setspawn &a[ID] &a[Number]");
	file.addDefault("SetEnabled.Success", "&aSetting &6ID:%ID% &ato &5%IsEnabled%");
	file.addDefault("SetEnabled.InvalidUsage", "&c/<lbwcmd> setenabled &a[ID] &a[true/false]");
	file.addDefault("ForceStart.Success", "&aStarted &6ID:%ID%");
	file.addDefault("ForceStart.InvalidUsage", "&c/<lbwcmd> forcestart &6[ID]");
	file.addDefault("EndGame.Success", "&aEnded &6ID:%ID%");
	file.addDefault("EndGame.InvalidUsage", "&c/<lbcmd> endgame &6[ID]");
	file.addDefault("EndGame.AlreadyEnded", "&cThis Game is already ended!");
	file.addDefault("SetMoney.Success", "&aSetting &5%Player%&a's Money to &6%Money%");
	file.addDefault("SetMoney.InvalidUsage", "&c/<lbcmd> setmoney &6(Money) [Player]");
	file.addDefault("Fly.Fly", "&aYou are flying now!");
	file.addDefault("Fly.UnFly", "&aDisabled flying!");
	file.addDefault("Fly.Error", "&cYou are not a spectator in a game!");
	file.addDefault("Backpack.null", "&4null!");
	file.addDefault("Backpack.open", "&aOpened Backpack!");
	file.addDefault("BombBlock.place", "&2a Bomb has been placed!");
	file.addDefault("BombBlock.break", "&7a Bomb has been destroyed!");
	file.addDefault("TameItem.Success", "&eTamed dog!");
	file.addDefault("TameItem.AlreadyTamed", "&cThis dog is already tamed!");
	file.addDefault("TakerItem.use", "&bSpawned Lucky Block Item!");
	file.addDefault("SuperCookie.eat", "&eCookie has been eaten!");
	file.addDefault("SuperCookie.hit", "&ePoisoned &6%Type%");
	file.addDefault("C4.Explode", "&e&lBoom!");
	file.addDefault("C4.Place", "&ePlaced C4!");
	file.addDefault("Trap.Place", "&ePlaced Trapped Item!");
	file.addDefault("Trap.Pickup", "&2Picked up trapped item!!!");
	file.addDefault("Cow.Mount", "&aYou are riding cow now!");
	file.addDefault("Cow.AlreadyRiding", "&cYou are already in a vehicle!");
	file.addDefault("TeleportItem.Teleport", "&aTeleported!");
	file.addDefault("TeleportItem.NoEntity", "&cNo Entity found!");
	file.addDefault("C4Block.Explode", "&e&lBoom!!!");
	file.addDefault("C4Block.Place", "&aPlaced C4 Block!");
	file.addDefault("C4Block.Break", "&cDestroyed C4 Block!");
	file.addDefault("CraftWrongItem", "&cWrong Item!");
	file.addDefault("SetMinPlayers.Success", "&aSetting Minimum Players for &6ID:%ID% &ato &5%Number%");
	file.addDefault("SetMinPlayers.InvalidUsage", "&c/<lbwcmd> setminplayers &6[ID] (Number)");
	file.addDefault("ChangeName.Success", "&aSetting Name to &r%Name%");
	file.addDefault("ChangeName.InvalidUsage", "&c/<lbwcmd> changename &6[Name] [Player]");
	file.addDefault("SetLevel.Success", "&aSetting Level to &6%Level%");
	file.addDefault("SetLevel.InvalidUsage", "&c/<lbwcmd> setlevel &6[Level] [Player]");
	file.addDefault("GetPlayer.Success", "&aOpened Player's Gui!");
	file.addDefault("GetPlayer.InvalidUsage", "&c/<lbwcmd> getplayer &6[Player]");
	file.addDefault("Save.Success", "&aSaved!");
	file.addDefault("Save.InvalidUsage", "&c/<lbwcmd> save &6[ID]");
	file.addDefault("SetBounds.Success", "&aSetting Bound.");
	file.addDefault("SetBounds.InvalidUsage", "&c/<lbwcmd> setbounds &6[ID] (Number)");
	file.addDefault("SetBounds.Error", "&cChoose between 1 and 2");
	file.addDefault("SetGameName.Success", "&aSetting Name to &6%Name%");
	file.addDefault("SetGameName.InvalidUsage", "&c/<lbwcmd> setgamename &6[ID] (Name)");
	file.addDefault("JoinTeam.Success", "&aYou have joined a team!");
	file.addDefault("JoinTeam.InvalidUsage", "&c/<tcmd> join &6[Name]");
	file.addDefault("JoinTeam.SendRequest", "&aSending a request!");
	file.addDefault("JoinTeam.SendRequest1", "&6%Player% &ahas sent a request!");
	file.addDefault("Team.Full", "&cThis team is full!");
	file.addDefault("Team.Error", "&cYou are already in a team!");
	file.addDefault("Team.Error1", "&cYou are not in a team!");
	file.addDefault("Team.Error2", "&cYou are not the owner of this team!");
	file.addDefault("Team.Error3", "&cThis team is already exist!");
	file.addDefault("Team.InvalidTeam", "&cInvalid Team!");
	file.addDefault("LeaveTeam.Success", "&aYou have left the team!");
	file.addDefault("Requests.Error", "&cNo Request Found!");
	file.addDefault("Accept.Success", "&aAccepted Player!");
	file.addDefault("Accept.InvalidUsage", "&c/<tcmd> accept &6[Player]");
	file.addDefault("SetTeamOwner.Success", "&aSetting Team's Owner to &6%Player%.");
	file.addDefault("SetTeamOwner.InvalidUsage", "&c/<tcmd> setowner &6[Team] [Player]");
	file.addDefault("CreateTeam.Success", "&aCreated Team Successfully!");
	file.addDefault("CreateTeam.InvalidUsage", "&c/<tcmd> create &6[TeamName]");
	file.addDefault("RemoveTeam.Success", "&aRemoved Team Successfully!");
	file.addDefault("RemoveTeam.InvalidUsage", "&c/<tcmd> remove &6[TeamName]");
	file.addDefault("ClearTeams.Success", "&aCleared &6%Number% &aTeam/s");
	file.addDefault("ClearTeams.NoTeamFound", "&cNo Team Found!");
	file.addDefault("TeamKick.Success", "&aTrying to kick &6%Player%");
	file.addDefault("SetCenter.Success", "&aSetting Center.");
	file.addDefault("SetCenter.InvalidUsage", "&c/<lbwcmd> setcenter &6[ID]");
	file.addDefault("ChooseKit.Success", "&aYou choosed &6%ItemName%");
	file.addDefault("ChooseKit.NoPermission", "&cYou don't have permission for this kit!");
	file.addDefault("ThoraxeCommand.Success", "&aGiving Thor's axe!");
	file.addDefault("DetectorCommand.Success", "&aGiving Detector!");
	file.addDefault("AddDBlock.Success", "&aAdded &6%Block% &ato the list!");
	file.addDefault("AddDBlock.InvalidUsage", "&c/<lbwcmd> adddblock &6[ID] [BlockID]");
	file.addDefault("AddDBlock.InvalidBlock", "&cInvalid block!");
	file.addDefault("Detector.Search", "&aSearching for nearby lucky blocks.");
	file.addDefault("Detector.Fail", "&cNo %lb% found!");
	file.addDefault("Detector.Place", "&aPlaced Detector!");
	file.addDefault("Detector.Break", "&cDestroyed Detector!");
	file.addDefault("SetRange.Success", "&aSetting range.");
	file.addDefault("SetRange.InvalidUsage", "&c/<lbcmd> setrange &6[ID] (Range)");
	file.addDefault("SetRange.NotFound", "&cDetector not found!");
	file.addDefault("GiveLB.Success", "&aGiving lucky block/s!");
	file.addDefault("GiveLB.InvalidLuck", "&cInvalid Luck!");
	file.addDefault("GiveLB.InvalidType", "&cInvalid Type!");
	file.addDefault("BreakLBSign", "&cDestroyed Lucky Block Sign!");
	file.addDefault("SetGold.Success", "&aSetting gold to &6%Gold%");
	file.addDefault("SetGold.InvalidUsage", "&c/<lbcmd> setgold &6(Gold) [Player]");
	file.addDefault("ClearSpawns.Success", "&aRemoved Spawns!");
	file.addDefault("ClearSpawns.InvalidUsage", "&c/<lbwcmd> clearspawns &6[ID]");
	file.addDefault("SetType.Success", "&aSetting type.");
	file.addDefault("SetType.InvalidUsage", "&c/<lbwcmd> settype &6[ID] [Type]");
	file.addDefault("SetType.InvalidType", "&cInvalid Type!");
	file.addDefault("SetAllowGates.Success", "&aChanging AllowGates option.");
	file.addDefault("SetAllowGates.InvalidUsage", "&c/<lbwcmd> setallowgates &6[ID] [true/false]");
	file.addDefault("Money.You", "&aYou have &6%Money%");
	file.addDefault("Money.Others", "&5%Target% &ahas &6%Money%");
	file.addDefault("Gold.You", "&aYou have &6%Gold%");
	file.addDefault("Gold.Others", "&5%Target% &ahas &6%Gold%");
	file.addDefault("Endall.Success", "&aEnded &6%Total% &aGame/s!");
	file.addDefault("RandomMap.Error", "&cCouldn't connect to a game!");
	file.addDefault("TpWorld.Success", "&aTeleporting.");
	file.addDefault("CreateLBShopSign", "&aCreated Lucky Block Shop Sign!");
	file.addDefault("CreateGame", "&aCreated sign for game &6%ID%!");
	file.addDefault("InvalidID", "&cInvalid ID!");
	file.addDefault("NoPermissionGame", "&cYou don't have permission to join this game!");
	file.addDefault("InvalidLobby", "&cLobby can't be found!");
	file.addDefault("JoinGame", "&aYou have just joined game &6%Name%!");
	file.addDefault("GameAlreadyStarted", "&cThis game is already started!");
	file.addDefault("GameFull", "&cFull!");
	file.addDefault("LoseGame", "&cYou Lose!");
	file.addDefault("PlayerSpectator", "&eYou are a spectator now!");
	file.addDefault("NoSpawnFound", "&cNo Spawn Found!");
	file.addDefault("WinGame.1", "&aYou won a game!");
	file.addDefault("WinGame.2", "&6%Player% &ahas won a game in map:&6%Name%");
	file.addDefault("GameEnded", "&cThe game has been ended!");
	file.addDefault("GameStarted", "&aThe game has started!");
	file.addDefault("InvalidSpawn", "&cInvalid Spawn!");
	file.addDefault("InvalidMainLobby", "&cMain lobby can't be found!");
	file.addDefault("BossSpawned", "&aA boss has been spawned!");
	file.addDefault("TimeStarted.1", "&a%StartTime% seconds until the game starts. prepare yourself.");
	file.addDefault("TimeStarted.2", "&eTime until start %StartTime%");
	file.addDefault("LevelUp", "&aLevel up!");
	file.addDefault("Damage", "&cThat hurts!");
	file.addDefault("StuckTrap", "&cYou have been stucked in teleportation!");
	file.addDefault("SnowMovingDisabled", "&cSnow Moving is disabled!");
	file.addDefault("TreeSpawned", "&aSpawned tree!");
	file.addDefault("Heal", "&aYou have been healed!");
	file.addDefault("Feed", "&aYou have been feed!");
	file.addDefault("EnchantItem.Success", "&aEnchanted item in your hand!");
	file.addDefault("EnchantItem.Fail", "&cCouldn't enchant item in hand!");
	file.addDefault("PoisonEntities", "&aPoisoned nearby entities!");
	file.addDefault("GetGift", "&aYou have got +1 Gift! (You can use /lb gift to use it).");
	file.addDefault("RemoveHarmfulEffects", "&aRemoved harmful effects!");
	file.addDefault("SnowMovingStarted", "&aYou have Snow Moving for %SnowTime% seconds now!");
	file.addDefault("RepairItems", "&aRepaired Items!");
	file.addDefault("GetMoreHealth", "&aYou got &6%health% &aHealth!");
	file.addDefault("GetFile.Success", "&aSuccesfully changed value!");
	file.addDefault("OpenGui.Success", "&aOpened gui!");
	file.addDefault("Gui.GetLB", "&aGiving luckyblock!");
	file.options().copyDefaults(true);
	try {
	file.save(fileF);
	} catch (IOException e){
	e.printStackTrace();
	}
	}
	
	
	
}