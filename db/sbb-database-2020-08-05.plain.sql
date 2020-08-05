--
-- PostgreSQL database dump
--

-- Dumped from database version 12.3
-- Dumped by pg_dump version 12.3

-- Started on 2020-08-04 19:57:51

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 202 (class 1259 OID 16801)
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 203 (class 1259 OID 16803)
-- Name: passenger; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.passenger (
    id numeric(19,2) NOT NULL,
    birthday date,
    name character varying(255),
    surname character varying(255)
);


ALTER TABLE public.passenger OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 16809)
-- Name: passenger_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.passenger_id_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.passenger_id_seq OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 16811)
-- Name: route; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.route (
    id numeric(19,2) NOT NULL,
    id_station_from numeric(19,2),
    id_station_to numeric(19,2)
);


ALTER TABLE public.route OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 16814)
-- Name: route_segment; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.route_segment (
    id_route numeric(19,2) NOT NULL,
    id_segment numeric(19,2) NOT NULL
);


ALTER TABLE public.route_segment OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 16817)
-- Name: segment; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.segment (
    id numeric(19,2) NOT NULL,
    arrival timestamp without time zone,
    cancelled boolean,
    delayed boolean,
    departure timestamp without time zone,
    price integer,
    tickets_left integer,
    id_station_from numeric(19,2),
    id_station_to numeric(19,2),
    id_train character varying(255)
);


ALTER TABLE public.segment OWNER TO postgres;

--
-- TOC entry 208 (class 1259 OID 16820)
-- Name: station; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.station (
    id numeric(19,2) NOT NULL,
    name character varying(255),
    id_zone character varying(255)
);


ALTER TABLE public.station OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 16826)
-- Name: ticket; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ticket (
    id numeric(19,2) NOT NULL,
    id_passenger numeric(19,2)
);


ALTER TABLE public.ticket OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 16829)
-- Name: ticket_segment; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ticket_segment (
    id_segment numeric(19,2) NOT NULL,
    id_ticket numeric(19,2) NOT NULL
);


ALTER TABLE public.ticket_segment OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 16832)
-- Name: train; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.train (
    id character varying(255) NOT NULL,
    seats_count integer
);


ALTER TABLE public.train OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 16835)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id numeric(19,2) NOT NULL,
    is_passenger boolean,
    login character varying(255) NOT NULL,
    password character varying(255),
    id_passenger numeric(19,2)
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 16841)
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_id_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_id_seq OWNER TO postgres;

--
-- TOC entry 2882 (class 0 OID 16803)
-- Dependencies: 203
-- Data for Name: passenger; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.passenger VALUES (1.00, '1990-09-15', 'Zoe', 'Miller');
INSERT INTO public.passenger VALUES (2.00, '1988-01-27', 'Mark', 'Smith');
INSERT INTO public.passenger VALUES (355.00, '1999-01-01', 'Lena', 'Meyer');


--
-- TOC entry 2884 (class 0 OID 16811)
-- Dependencies: 205
-- Data for Name: route; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.route VALUES (1.00, 1.00, 4.00);
INSERT INTO public.route VALUES (2.00, 2.00, 4.00);
INSERT INTO public.route VALUES (3.00, 1.00, 2.00);
INSERT INTO public.route VALUES (4.00, 1.00, 3.00);
INSERT INTO public.route VALUES (5.00, 2.00, 3.00);
INSERT INTO public.route VALUES (6.00, 3.00, 4.00);


--
-- TOC entry 2885 (class 0 OID 16814)
-- Dependencies: 206
-- Data for Name: route_segment; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.route_segment VALUES (1.00, 1.00);
INSERT INTO public.route_segment VALUES (1.00, 2.00);
INSERT INTO public.route_segment VALUES (1.00, 3.00);
INSERT INTO public.route_segment VALUES (2.00, 4.00);
INSERT INTO public.route_segment VALUES (2.00, 5.00);
INSERT INTO public.route_segment VALUES (3.00, 1.00);
INSERT INTO public.route_segment VALUES (4.00, 1.00);
INSERT INTO public.route_segment VALUES (4.00, 2.00);
INSERT INTO public.route_segment VALUES (5.00, 2.00);
INSERT INTO public.route_segment VALUES (6.00, 3.00);
INSERT INTO public.route_segment VALUES (2.00, 2.00);
INSERT INTO public.route_segment VALUES (2.00, 3.00);
INSERT INTO public.route_segment VALUES (5.00, 4.00);
INSERT INTO public.route_segment VALUES (6.00, 5.00);


--
-- TOC entry 2886 (class 0 OID 16817)
-- Dependencies: 207
-- Data for Name: segment; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.segment VALUES (1.00, '2020-10-17 12:00:00', false, false, '2020-10-12 13:10:00', 300, 15, 1.00, 2.00, '121a');
INSERT INTO public.segment VALUES (2.00, '2020-10-18 11:00:00', false, false, '2020-10-17 12:10:00', 110, 15, 2.00, 3.00, '121a');
INSERT INTO public.segment VALUES (3.00, '2020-10-18 19:20:00', false, false, '2020-10-18 11:30:00', 100, 15, 3.00, 4.00, '121a');
INSERT INTO public.segment VALUES (4.00, '2020-10-21 10:40:00', false, false, '2020-10-18 09:15:00', 480, 30, 2.00, 3.00, '765b');
INSERT INTO public.segment VALUES (5.00, '2020-10-22 10:10:00', false, false, '2020-10-21 11:00:00', 270, 30, 3.00, 4.00, '765b');


--
-- TOC entry 2887 (class 0 OID 16820)
-- Dependencies: 208
-- Data for Name: station; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.station VALUES (1.00, 'Green Hills', 'UTC');
INSERT INTO public.station VALUES (2.00, 'Red Volcano', 'Europe/Moscow');
INSERT INTO public.station VALUES (3.00, 'Seaside Hill', 'Asia/Omsk');
INSERT INTO public.station VALUES (4.00, 'Chemical Plant', 'Asia/Irkutsk');


--
-- TOC entry 2888 (class 0 OID 16826)
-- Dependencies: 209
-- Data for Name: ticket; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.ticket VALUES (1.00, 1.00);
INSERT INTO public.ticket VALUES (2.00, 2.00);


--
-- TOC entry 2889 (class 0 OID 16829)
-- Dependencies: 210
-- Data for Name: ticket_segment; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.ticket_segment VALUES (1.00, 1.00);
INSERT INTO public.ticket_segment VALUES (2.00, 1.00);
INSERT INTO public.ticket_segment VALUES (3.00, 1.00);
INSERT INTO public.ticket_segment VALUES (4.00, 2.00);
INSERT INTO public.ticket_segment VALUES (5.00, 2.00);


--
-- TOC entry 2890 (class 0 OID 16832)
-- Dependencies: 211
-- Data for Name: train; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.train VALUES ('1', 21);
INSERT INTO public.train VALUES ('121a', 15);
INSERT INTO public.train VALUES ('765b', 30);


--
-- TOC entry 2891 (class 0 OID 16835)
-- Dependencies: 212
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.users VALUES (2.00, true, 'mark', '$2a$10$QI7tmtnh8zEiocNPZPALFOXPqFBFMn96kyF0AZ5lbag2XBaF5wdim', 2.00);
INSERT INTO public.users VALUES (1.00, true, 'zmiller', '$2a$10$4h/Fb52vLA14.RTlV9ofEOcPbUgIvFCAzYVdLwT4jwjLbbxuYC6.O', 1.00);
INSERT INTO public.users VALUES (356.00, true, 'lenochka', '$2a$10$uJXgwljpgXEfyld6i6wgr.czOHUL/nbvlTlvaEu5jrur5nLO9XO3y', 355.00);
INSERT INTO public.users VALUES (3.00, false, 'alyonakova', '$2a$10$lO4zaVRn273CTfjpGEY3ludNmNkWp0QlbX0.FtTOyMwsQeXiL2Ogm', NULL);


--
-- TOC entry 2898 (class 0 OID 0)
-- Dependencies: 202
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.hibernate_sequence', 1, false);


--
-- TOC entry 2899 (class 0 OID 0)
-- Dependencies: 204
-- Name: passenger_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.passenger_id_seq', 403, true);


--
-- TOC entry 2900 (class 0 OID 0)
-- Dependencies: 213
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_id_seq', 404, true);


--
-- TOC entry 2727 (class 2606 OID 16844)
-- Name: passenger passenger_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.passenger
    ADD CONSTRAINT passenger_pkey PRIMARY KEY (id);


--
-- TOC entry 2729 (class 2606 OID 16846)
-- Name: route route_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.route
    ADD CONSTRAINT route_pkey PRIMARY KEY (id);


--
-- TOC entry 2731 (class 2606 OID 16848)
-- Name: segment segment_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.segment
    ADD CONSTRAINT segment_pkey PRIMARY KEY (id);


--
-- TOC entry 2733 (class 2606 OID 16850)
-- Name: station station_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.station
    ADD CONSTRAINT station_pkey PRIMARY KEY (id);


--
-- TOC entry 2735 (class 2606 OID 16852)
-- Name: ticket ticket_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ticket
    ADD CONSTRAINT ticket_pkey PRIMARY KEY (id);


--
-- TOC entry 2737 (class 2606 OID 16854)
-- Name: train train_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.train
    ADD CONSTRAINT train_pkey PRIMARY KEY (id);


--
-- TOC entry 2739 (class 2606 OID 16856)
-- Name: users uk_5wtf0rjssg8l8uh6i4kisc4yn; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT uk_5wtf0rjssg8l8uh6i4kisc4yn UNIQUE (id_passenger);


--
-- TOC entry 2741 (class 2606 OID 16858)
-- Name: users uk_ow0gan20590jrb00upg3va2fn; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT uk_ow0gan20590jrb00upg3va2fn UNIQUE (login);


--
-- TOC entry 2743 (class 2606 OID 16860)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 2748 (class 2606 OID 16861)
-- Name: segment fk3sk7mntnng0dt21dwj31ee7wx; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.segment
    ADD CONSTRAINT fk3sk7mntnng0dt21dwj31ee7wx FOREIGN KEY (id_train) REFERENCES public.train(id);


--
-- TOC entry 2744 (class 2606 OID 16866)
-- Name: route fkckglvsinas90bpj54av2hjnjs; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.route
    ADD CONSTRAINT fkckglvsinas90bpj54av2hjnjs FOREIGN KEY (id_station_to) REFERENCES public.station(id);


--
-- TOC entry 2749 (class 2606 OID 16871)
-- Name: segment fkdbmjlymqeu3e6639vl4mcckg6; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.segment
    ADD CONSTRAINT fkdbmjlymqeu3e6639vl4mcckg6 FOREIGN KEY (id_station_to) REFERENCES public.station(id);


--
-- TOC entry 2752 (class 2606 OID 16876)
-- Name: ticket_segment fkdwbnxiodqt54x894we2qaimr8; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ticket_segment
    ADD CONSTRAINT fkdwbnxiodqt54x894we2qaimr8 FOREIGN KEY (id_segment) REFERENCES public.segment(id);


--
-- TOC entry 2746 (class 2606 OID 16881)
-- Name: route_segment fkg9c858ll2pfieh7hbuovuu66r; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.route_segment
    ADD CONSTRAINT fkg9c858ll2pfieh7hbuovuu66r FOREIGN KEY (id_route) REFERENCES public.route(id);


--
-- TOC entry 2750 (class 2606 OID 16886)
-- Name: segment fkibvdf7arr01msjufcu7r5f0jw; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.segment
    ADD CONSTRAINT fkibvdf7arr01msjufcu7r5f0jw FOREIGN KEY (id_station_from) REFERENCES public.station(id);


--
-- TOC entry 2753 (class 2606 OID 16891)
-- Name: ticket_segment fklum0u81ifnbll9ndthg6x86gf; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ticket_segment
    ADD CONSTRAINT fklum0u81ifnbll9ndthg6x86gf FOREIGN KEY (id_ticket) REFERENCES public.ticket(id);


--
-- TOC entry 2745 (class 2606 OID 16896)
-- Name: route fkmrv002678wu8mxl2c5snsespf; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.route
    ADD CONSTRAINT fkmrv002678wu8mxl2c5snsespf FOREIGN KEY (id_station_from) REFERENCES public.station(id);


--
-- TOC entry 2751 (class 2606 OID 16901)
-- Name: ticket fkq2a5lvhfo0ko6sc1em9d2ejk0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ticket
    ADD CONSTRAINT fkq2a5lvhfo0ko6sc1em9d2ejk0 FOREIGN KEY (id_passenger) REFERENCES public.passenger(id);


--
-- TOC entry 2747 (class 2606 OID 16906)
-- Name: route_segment fkrd6da8jjmrd0pqt1td03d3ey6; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.route_segment
    ADD CONSTRAINT fkrd6da8jjmrd0pqt1td03d3ey6 FOREIGN KEY (id_segment) REFERENCES public.segment(id);


--
-- TOC entry 2754 (class 2606 OID 16911)
-- Name: users fktljck3po78mn5835x4vgry2q; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT fktljck3po78mn5835x4vgry2q FOREIGN KEY (id_passenger) REFERENCES public.passenger(id);


-- Completed on 2020-08-04 19:57:52

--
-- PostgreSQL database dump complete
--

