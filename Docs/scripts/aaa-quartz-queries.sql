
SELECT SCHED_NAME,LOCK_NAME FROM QRTZ_LOCKS;

INSERT INTO qrtz_locks values('TRIGGER_ACCESS');
INSERT INTO qrtz_locks values('JOB_ACCESS');
INSERT INTO qrtz_locks values('CALENDAR_ACCESS');
INSERT INTO qrtz_locks values('STATE_ACCESS');
INSERT INTO qrtz_locks values('MISFIRE_ACCESS');





INSERT INTO qrtz_locks values('','TRIGGER_ACCESS');
INSERT INTO qrtz_locks values('','JOB_ACCESS');
INSERT INTO qrtz_locks values('','CALENDAR_ACCESS');
INSERT INTO qrtz_locks values('','STATE_ACCESS');
INSERT INTO qrtz_locks values('','MISFIRE_ACCESS');




SELECT SCHED_NAME,LOCK_NAME FROM QRTZ_LOCKS;

