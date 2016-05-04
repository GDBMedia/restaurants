--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: cuisines; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE cuisines (
    id integer NOT NULL,
    cuisine character varying
);


ALTER TABLE cuisines OWNER TO "Guest";

--
-- Name: cuisines_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE cuisines_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cuisines_id_seq OWNER TO "Guest";

--
-- Name: cuisines_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE cuisines_id_seq OWNED BY cuisines.id;


--
-- Name: rests; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE rests (
    id integer NOT NULL,
    rest character varying,
    cuisine character varying,
    description text,
    dish character varying
);


ALTER TABLE rests OWNER TO "Guest";

--
-- Name: rests_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE rests_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE rests_id_seq OWNER TO "Guest";

--
-- Name: rests_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE rests_id_seq OWNED BY rests.id;


--
-- Name: reviews; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE reviews (
    id integer NOT NULL,
    review text,
    restid integer
);


ALTER TABLE reviews OWNER TO "Guest";

--
-- Name: reviews_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE reviews_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE reviews_id_seq OWNER TO "Guest";

--
-- Name: reviews_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE reviews_id_seq OWNED BY reviews.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY cuisines ALTER COLUMN id SET DEFAULT nextval('cuisines_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY rests ALTER COLUMN id SET DEFAULT nextval('rests_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY reviews ALTER COLUMN id SET DEFAULT nextval('reviews_id_seq'::regclass);


--
-- Data for Name: cuisines; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY cuisines (id, cuisine) FROM stdin;
1	Italian
2	Pizza
3	Mexican
4	Burger
5	Food Carts
\.


--
-- Name: cuisines_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('cuisines_id_seq', 5, true);


--
-- Data for Name: rests; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY rests (id, rest, cuisine, description, dish) FROM stdin;
1	Sizzle Pie	Pizza	Classic and unconventional pizza, a selection of vegan, veggie & omnivore options. 35+ beers, wine and a great jukebox. Gluten free options available.	Pig Destroyer
\.


--
-- Name: rests_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('rests_id_seq', 1, true);


--
-- Data for Name: reviews; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY reviews (id, review, restid) FROM stdin;
\.


--
-- Name: reviews_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('reviews_id_seq', 1, false);


--
-- Name: cuisines_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY cuisines
    ADD CONSTRAINT cuisines_pkey PRIMARY KEY (id);


--
-- Name: rests_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY rests
    ADD CONSTRAINT rests_pkey PRIMARY KEY (id);


--
-- Name: reviews_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY reviews
    ADD CONSTRAINT reviews_pkey PRIMARY KEY (id);


--
-- Name: public; Type: ACL; Schema: -; Owner: epicodus
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM epicodus;
GRANT ALL ON SCHEMA public TO epicodus;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

