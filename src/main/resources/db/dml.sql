alter table license_bean_list_module_view drop constraint uk_qvwv0d6un292ga28trjj2pg9b;
alter table license_bean_list_module_methodology drop constraint uk_k83e3pl3stku5ibjff9g32lh0;
alter table license_bean_list_module_evaluation drop constraint uk_6bpkgvrsvnpepqva5sgfp6hb6;
alter table license_bean_list_module drop constraint uk_49pw7of4lhysao6u70t61be4e;
alter table license_bean_list_module_audit drop constraint uk_okh1hliqshuisjjmyyxiba4up;

insert into skill_bean (id, name, description, pic) values (1,
	'Lightning Storm',
	'Active - 3 random enemies take 120% of Attack damage and have a 80% chance to become stunned for 2 round if target is Mage', 
	'https://i.ibb.co/xXSgc2Z/lightning-storm.png'
);

