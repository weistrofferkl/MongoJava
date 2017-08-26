import com.mongodb.*;

public class Week3Assignment1 {
    public static void main(String[] args){
        MongoClient client = new MongoClient();
        DB db = client.getDB("school");
        DBCollection col = db.getCollection("students");
        DBCursor cursor = col.find();

        try {
            while (cursor.hasNext()) {

                BasicDBObject student = (BasicDBObject) cursor.next();
                int sID = student.getInt("_id");
                String sName = student.getString("name");
                BasicDBList scoreList = (BasicDBList) student.get("scores");

                double minScore = 100.00;
                DBObject deadScore = null;

                for (Object ob : scoreList) {

                    BasicDBObject selectedScore = (BasicDBObject) ob;
                    String assignmentType = selectedScore.getString("type");

                    double curScore = selectedScore.getDouble("score");
                    if ("homework".equals(assignmentType) && curScore < minScore) {
                        deadScore = selectedScore;
                        minScore = curScore;

                    }


                }

                if (deadScore != null) {
                    scoreList.remove(deadScore);

                    BasicDBObject query = new BasicDBObject("_id", sID);
                    BasicDBObject updateScores = new BasicDBObject("$set", new BasicDBObject("scores", scoreList));
                    WriteResult Wres = col.update(query, updateScores);
                }

            }
        }
        finally{
            cursor.close();
        }

    }

}
