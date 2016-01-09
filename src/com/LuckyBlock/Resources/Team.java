package com.LuckyBlock.Resources;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.LuckyBlock.Engine.LuckyBlock;
import com.LuckyBlock.Engine.LuckyBlockAPI;

public class Team {
	
	
	private int id;
	private String name;
	private List<UUID> players = new ArrayList<UUID>();
	private List<String> requests = new ArrayList<String>();
	private UUID owner;
	private List<String> accepted = new ArrayList<String>();
	private List<String> kicked = new ArrayList<String>();
	
	
	
	public Team(String name, int id){
	this.name = name;
	this.id = id;
	}
	
	
	
	public static Team fromId(int id){
	Team t = null;
	for(int x = 0; x < LuckyBlockAPI.teams.size(); x++){
	Team team = LuckyBlockAPI.teams.get(x);
	if(team.getId() == id){
	t = team;
	}
	}
	return t;
	}
	
	
	
	public static Team fromName(String name){
	Team t = null;
	for(int x = 0; x < LuckyBlockAPI.teams.size(); x++){
	Team team = LuckyBlockAPI.teams.get(x);
	if(team.getName().equalsIgnoreCase(name)){
	t = team;
	}
	}
	return t;
	}
	
	
	
	public static boolean isPlayerInTeam(UUID uuid){
	boolean b = false;
	for(int x = 0; x < LuckyBlockAPI.teams.size(); x++){
	Team t = LuckyBlockAPI.teams.get(x);
	if(t.containsPlayer(uuid)){
	b = true;
	}
	}
	return b;
	}
	
	
	
	public static Team fromPlayer(UUID uuid){
	Team t = null;
	for(int x = 0; x < LuckyBlockAPI.teams.size(); x++){
	Team team = LuckyBlockAPI.teams.get(x);
	if(team.containsPlayer(uuid)){
	t = team;
	}
	}
	return t;
	}
	
	
	
	public void setId(int id){
	this.id = id;
	}
	
	
	
	public void setOwner(UUID owner){
	this.owner = owner;
	}
	
	
	
	public List<String> getAccepted(){
	return accepted;
	}
	
	
	
	public void addAccepted(String name){
	accepted.add(name);
	}
	
	
	
	public void setAccepted(List<String> accepted){
	this.accepted = accepted;
	}
	
	
	
	public void removeAccepted(String name){
	if(accepted.contains(name)){
	accepted.remove(name);
	}
	}
	
	
	
	public void kick(String name){
	if(kicked.contains(name)){
	kicked.remove(name);
	}
	kicked.add(name);
	searchKickedPlayer(name);
	}
	
	
	
	public void searchKickedPlayer(String name){
	if(kicked.contains(name)){
	for(Player p : Bukkit.getOnlinePlayers()){
	if(p.getName().equalsIgnoreCase(name)){
	UUID uuid = p.getUniqueId();
	kicked.remove(name);
	removePlayer(uuid);
	p.sendMessage(ChatColor.RED + "You have been kicked from the team!");
	}
	}
	}
	}
	
	
	
	public List<String> getKicked(){
	return kicked;
	}
	
	
	
	public void setKicked(List<String> kicked){
	this.kicked = kicked;
	}
	
	
	
	public int getId(){
	return this.id;
	}
	
	
	
	public UUID getOwner(){
	return owner;
	}
	
	
	
	public boolean isOwner(UUID uuid){
	boolean b = false;
	if(owner != null){
	if(owner == uuid){
	b = true;
	}
	}
	return b;
	}
	
	
	
	public void setName(String name) {
	this.name = name;
	}
	
	
	
	public String getName() {
	return name;
	}
	
	
	
	public void setPlayers(List<UUID> players){
	this.players = players;
	}
	
	
	
	public void addPlayer(UUID uuid){
	players.add(uuid);
	}
	
	
	
	public void removePlayer(UUID uuid){
	if(players.contains(uuid)){
	players.remove(uuid);
	}
	}
	
	
	
	public void addRequest(String name){
	requests.add(name);
	}
	
	
	
	public void setRequests(List<String> requests){
	this.requests = requests;
	}
	
	
	
	public List<UUID> getPlayers(){
	return players;
	}
	
	
	
	public List<String> getRequests(){
	return requests;
	}
	
	
	
	public boolean containsPlayer(UUID uuid){
	return players.contains(uuid);
	}
	
	
	
	public String getRequest(int num){
	String s = null;
	if(requests.size() > num){
	s = requests.get(num);
	} else {
	throw new IllegalArgumentException("Number Cannot be Smaller than Array Size!");
	}
	return s;
	}
	
	
	
	public void acceptPlayer(String name){
	if(requests.contains(name)){
	requests.remove(name);
	}
	accepted.add(name);
	searchPlayer(name);
	}
	
	
	
	public void searchPlayer(String name){
	if(accepted.contains(name)){
	for(Player p : Bukkit.getOnlinePlayers()){
	if(p.getName().equalsIgnoreCase(name)){
	UUID uuid = p.getUniqueId();
	accepted.remove(name);
	addPlayer(uuid);
	p.sendMessage(ChatColor.GREEN + "You are in a team now!");
	}
	}
	}
	}
	
	
	
	public void save(){
	for(int x = 0; x < LuckyBlockAPI.teams.size(); x++){
	if(LuckyBlockAPI.teams.get(x).getId() == id){
	LuckyBlockAPI.teams.remove(x);
	}
	}
	LuckyBlockAPI.teams.add(this);
	LuckyBlock.instance.data.set("Teams.Team" + id + ".ID", id);
	LuckyBlock.instance.data.set("Teams.Team" + id + ".Name", name);
	if(requests.size() > 0){
	LuckyBlock.instance.data.set("Teams.Team" + id + ".Requests", requests);
	}
	if(owner != null){
	LuckyBlock.instance.data.set("Teams.Team" + id + ".Owner", owner.toString());
	}
	if(accepted.size() > 0){
	LuckyBlock.instance.data.set("Teams.Team" + id + ".Accepted", accepted);
	}
	if(kicked.size() > 0){
	LuckyBlock.instance.data.set("Teams.Team" + id + ".Kicked", kicked);
	}
	List<String> p = new ArrayList<String>();
	for(int x = 0; x < players.size(); x++){
	p.add(players.get(x).toString());
	}
	LuckyBlock.instance.data.set("Teams.Team" + id + ".Players", p);
	LuckyBlockAPI.saveConfigs();
	}
	
	
	
	public void dispose(){
	LuckyBlockAPI.teams.remove(this);
	LuckyBlock.instance.data.set("Teams.Team" + id, null);
	LuckyBlockAPI.saveConfigs();
	}
	
	
	
	
	
	
	
	
	
	
	
}
