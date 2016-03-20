//package WebAPI;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import Utils.HibernateUtil;
//import model.Korisnik;
//import org.hibernate.Query;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.IOException;
//import java.security.NoSuchAlgorithmException;
//import java.security.spec.InvalidKeySpecException;
//import java.util.Date;
//import java.util.List;
//
//@WebServlet(urlPatterns = {"/korisnik", "/korisnik/*"})
//public class KorisnikController extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//
//    private final String salt = "TestSalt";
//
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public KorisnikController() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//
//    /**
//     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//     */
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // TODO Auto-generated method stub
//
//        try {
//            if(true) {
//                Session session = HibernateUtil.getSessionFactory().openSession();
//                Transaction t = null;
//                try {
//                    t = session.beginTransaction();
//                    Query query = session.createQuery("select k from Korisnik k where k.deleted = '0'");
//                    List<Korisnik> korisnici = (List<Korisnik>) query.list();
//                    for(Korisnik korisnik: korisnici)
//                        for(int i = 0; i < korisnik.getKorisnikXKorisniks2().size(); i++)
//                            korisnik.getKorisnikXKorisniks2().remove(korisnik.getKorisnikXKorisniks2().get(0));
//                    ObjectMapper mapper = new ObjectMapper();
//                    String ret = mapper.writeValueAsString(korisnici);
//                    response.getWriter().print(ret);
//                    t.commit();
//                } catch (Throwable thr) {
//                    if (t != null) t.rollback();
//                    throw thr;
//                } finally {
//                    session.close();
//                }
//            }
//            else {
//                throw new Exception("Unauthorized");
//            }
//        } catch (Throwable throwable) {
//            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//            response.getWriter().println(throwable.getMessage());
//        }
//    }
//
//    /**
//     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//     */
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // TODO Auto-generated method stub
//        try {
//            if(true) {
//                StringBuffer sb = new StringBuffer();
//
//                BufferedReader reader = request.getReader();
//                String line = null;
//                while ((line = reader.readLine()) != null) {
//                    sb.append(line);
//                }
//                String test2 = sb.toString();
//                ObjectMapper mapper = new ObjectMapper();
//
//                Korisnik korisnik = mapper.readValue(test2, Korisnik.class);
//                korisnik.setDeleted('0');
//                //u bazu se ne upisuje password, nego hash passworda
//                try {
//                    korisnik.setPassword(korisnik.getPassword());
//                }
//                catch (Exception e) {
//                    throw new Exception("Nesto nije OK");
//                }
//                //korisnik.setDatumRodjenja(new Date());
//
//                Session session = HibernateUtil.getSessionFactory().openSession();
//                Transaction t = null;
//                try {
//                    t = session.beginTransaction();
//                    session.save(korisnik);
//
//                    t.commit();
//                    String ret = mapper.writeValueAsString(korisnik);
//                    response.getWriter().print(mapper.writeValueAsString(korisnik));
//                } catch (Throwable thr) {
//                    if (t != null) t.rollback();
//                    throw thr;
//                } finally {
//                    session.close();
//                }
//            }
//            else {
//                throw new Exception("Unauthorized");
//            }
//        } catch (Throwable throwable) {
//            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//            response.getWriter().println(throwable.getMessage());
//        }
//    }
//
//    /**
//     * @see HttpServlet#doPut(HttpServletRequest request, HttpServletResponse response)
//     */
//    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // TODO Auto-generated method stub
//        //mozda ne treba sve biti u try bloku, nema potrebe ako header nije setovan
//        try {
//            if(true) {
//                StringBuffer sb = new StringBuffer();
//
//                BufferedReader reader = request.getReader();
//                String line = null;
//                while ((line = reader.readLine()) != null) {
//                    sb.append(line);
//                }
//
//                String test2 = sb.toString();
//                ObjectMapper mapper = new ObjectMapper();
//
//                Korisnik korisnik = mapper.readValue(test2, Korisnik.class);
//                Session session = HibernateUtil.getSessionFactory().openSession();
//                Transaction t = null;
//                try {
//                    t = session.beginTransaction();
//                    Query query = session.createQuery("from Korisnik as k where k.id = ?");
//                    query.setLong(0, korisnik.getId());
//                    Korisnik forUpdate = (Korisnik) query.uniqueResult();
//                    forUpdate.setUsername(korisnik.getUsername());
//                    forUpdate.setIme(korisnik.getIme());
//                    forUpdate.setPrezime(korisnik.getPrezime());
//                    //forUpdate.setJmbg(korisnik.getJmbg());
//                    forUpdate.setDatumRodjenja(korisnik.getDatumRodjenja());
//                    //forUpdate.setMjestoRodjenja(korisnik.getMjestoRodjenja());
//                    forUpdate.setEmail(korisnik.getEmail());
//                    forUpdate.setPassword(korisnik.getPassword());
//                    t.commit();
//                } catch (Throwable thr) {
//                    if (t != null) t.rollback();
//                    throw thr;
//                } finally {
//                    session.close();
//                }
//            }
//            else {
//                throw new Exception("Unauthorized");
//            }
//        } catch (Throwable throwable) {
//            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//            response.getWriter().println(throwable.getMessage());
//        }
//    }
//
//    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // TODO Auto-generated method stub
//        //mozda ne treba sve biti u try bloku, nema potrebe ako header nije setovan
//        try {
//            if(true) {
//                StringBuffer sb = new StringBuffer();
//
//                BufferedReader reader = request.getReader();
//                String line = null;
//                while ((line = reader.readLine()) != null) {
//                    sb.append(line);
//                }
//
//                String test2 = sb.toString();
//                int id = Integer.parseInt(request.getPathInfo().substring(1));
//
//                Session session = HibernateUtil.getSessionFactory().openSession();
//                Transaction t = null;
//                try {
//                    t = session.beginTransaction();
//                    Query query = session.createQuery("update Korisnik as k set k.deleted = '1' where k.id = ?");
//                    query.setLong(0, id);
//                    int obrisani = query.executeUpdate();
//
//                    if(obrisani > 1)
//                        throw new Exception("Pokusaj brisanja vise od jedne akcije");
//                    t.commit();
//                } catch (Throwable thr) {
//                    if (t != null) t.rollback();
//                    throw thr;
//                } finally {
//                    session.close();
//                }
//            }
//            else {
//                throw new Exception("Unauthorized");
//            }
//        } catch (Throwable throwable) {
//            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//            response.getWriter().println(throwable.getMessage());
//        }
//    }
//
//}
