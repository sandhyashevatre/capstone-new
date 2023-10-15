
import ImageSlider from "./ImageSlider";

export default function Home() {
  const images = [
    "/images/IS1.jpg",
    "image2.jpg",
    "image3.jpg",
    // Add more image URLs as needed
  ];
  return (
    <div>
      <div className="imageslider">
          <ImageSlider images={images} glideDuration={5000} />
      </div>
    </div>
  );
}
