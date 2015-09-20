SELECT d.id_serv, id_seRVCAT,s.deSCRIPCION, r.DESCRIPCION 
   FROM SOLICITUD_DETALLE d,
               CAT_SERV s,
               CAT_SERVRESP r
 WHERE d.id_serv = s.id_serv
      AND s.id_servresp1 = r.id_servresp
  ;